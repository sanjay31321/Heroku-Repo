package com.sanjay31321.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sanjay31321.sys.model.Answer;
import com.sanjay31321.sys.model.Feedback;
import com.sanjay31321.sys.model.Feedback_Done;
import com.sanjay31321.sys.model.MyAnswers;
import com.sanjay31321.sys.model.Question;
import com.sanjay31321.sys.model.Student_Course;
import com.sanjay31321.sys.model.User;
import com.sanjay31321.sys.service.AnswerService;
import com.sanjay31321.sys.service.CourseService;
import com.sanjay31321.sys.service.CourseSubjectService;
import com.sanjay31321.sys.service.FeedbackDoneService;
import com.sanjay31321.sys.service.FeedbackService;
import com.sanjay31321.sys.service.QuestionService;
import com.sanjay31321.sys.service.QuestionSetService;
import com.sanjay31321.sys.service.StudentCourseService;
import com.sanjay31321.sys.service.SubjectService;
import com.sanjay31321.sys.service.TeacherSubjectService;
import com.sanjay31321.sys.service.UserService;

@Controller
public class FeedbackController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private QuestionSetService questionSetService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private StudentCourseService studentCourseService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseSubjectService courseSubjectService;
	@Autowired
	private FeedbackDoneService feedbackDoneService;
	@Autowired private AnswerService answerService;
	@Autowired private TeacherSubjectService teacherSubjectService;
	
	MyAnswers myAnswers;

	@RequestMapping(value = "/feedbackmanager", method = RequestMethod.GET)
	public String getfeedbackmanager(@ModelAttribute("feedback") Feedback feedback, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Feedback Manager page ! GET Method : The client locale is {}.",	locale);
		map.put("feedbackList", feedbackService.feedbackList());
		map.put("teacherSubjectList", teacherSubjectService.teacherSubjectList());
		return "feedbackmanager";
	}
	
	public class Count { int [] feedback_id; int [] count; }
	
	@RequestMapping(value = "/feedbackreport", method = RequestMethod.GET)
	public String getfeedbackreport(@ModelAttribute("feedback") Feedback feedback, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Feedback Manager page ! GET Method : The client locale is {}.",	locale);
		map.put("feedbackList", feedbackService.feedbackList());
		map.put("teacherSubjectList", teacherSubjectService.teacherSubjectList());
		return "feedbackreport";
	}
	
	@RequestMapping(value = "/viewreport", method = RequestMethod.GET)
	public String getviewreport(Locale locale, Map<String, Object> map, HttpServletRequest req, @RequestParam("id") Integer id) {
		logger.info("Welcome to View Feedback Report page ! GET Method : The client locale is {}.",	locale);
		Feedback feedback = feedbackService.getFeedback(id);
		map.put("answerList", answerService.getAnswerListByFeedbackId(feedback.getId()));
		map.put("questionList", questionService.getQuestionByQuestionSetID(feedback.getQuestion_set().getId()));
		map.put("feedback", feedbackService.getFeedback(id));
		return "viewreport";
	}
	
	@RequestMapping(value = "/createfeedback", method = RequestMethod.GET)
	public String getcreatefeedback(@ModelAttribute("feedback") Feedback feedback, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Create Feedback page ! GET Method : The client locale is {}.",locale);
		map.put("questionsetList", questionSetService.questionSetList());
		map.put("subjectList", subjectService.subjectList());
		return "createfeedback";
	}

	@RequestMapping(value = "/createfeedback", method = RequestMethod.POST)
	public String postcreatefeedback(@Valid @ModelAttribute("feedback") Feedback feedback,  final RedirectAttributes redirectAttributes, BindingResult result, Locale locale, HttpServletRequest req, Map<String, Object> map) throws ParseException {
		logger.info("Welcome to Create Feedback page ! POST Method : The client locale is {}.",locale);
		
		String email = req.getParameter("email");
		Date date_from= new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(req.getParameter("date_from"));
		Date date_to= new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(req.getParameter("date_to"));
		
		if(feedback.getQuestion_set().getId() == 0 && feedback.getSubject().getId() == 0){
			map.put("questionsetList", questionSetService.questionSetList());
			map.put("subjectList", subjectService.subjectList());
			redirectAttributes.addFlashAttribute("errorMsg","Please complete the form.");
			return "createfeedback";
		}
		
		if ((!feedbackService.feedbackExists(feedback.getName())) && (userService.userExist(email))) {
			feedback.setUser(userService.userExists(email));
			feedback.setCreated();
			
			feedback.setDate_from(date_from);
			feedback.setDate_to(date_to);
			feedbackService.addFeedback(feedback);
			logger.debug("Feedback Created");
			redirectAttributes.addFlashAttribute("Msg","Feedback Created");
			return "redirect:feedbackmanager.html";
		} else {
			redirectAttributes.addFlashAttribute("errorMsg","Feedback name already exists. Choose another.");
			return "redirect:createfeedback.html";
		}
	}

	@RequestMapping(value = "/editfeedback", method = RequestMethod.GET)
	public String geteditfeedback(@ModelAttribute("feedback") Feedback feedback, Locale locale, 
			Map<String, Object> map, HttpServletRequest req, @RequestParam("id") Integer id, BindingResult result) {
		logger.info("Welcome to Edit Feedback page ! GET Method : The client locale is {}.",locale);
		
		feedback=feedbackService.getFeedback(id);
		map.put("feedback",feedback);
		map.put("questionsetList", questionSetService.questionSetList());
		map.put("subjectList", subjectService.subjectList());
		return "editfeedback";
	}
	
	@RequestMapping(value = "/editfeedback", method = RequestMethod.POST)
	public String postaseditfeedback(
			@ModelAttribute("feedback") Feedback feedback, BindingResult result, Locale locale, HttpServletRequest req,
			Map<String, Object> map,  final RedirectAttributes redirectAttributes) throws ParseException {
		
		
		logger.info("Welcome to Edit Feedback page ! POST Method : The client locale is {}.",locale);

		String email = req.getParameter("email");
		
		Date date_from = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(req.getParameter("date_from"));
		Date date_to = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(req.getParameter("date_to"));

		Feedback check = feedbackService.getFeedback(feedback.getId());

		if (check != null) {
			feedback.setUser(userService.userExists(email));
			feedback.setCreated(check.getCreated());
			feedback.setDate_from(date_from);
			feedback.setDate_to(date_to);
			feedbackService.editFeedback(feedback);
			logger.debug("Feedback Updated");
			redirectAttributes.addFlashAttribute("Msg","Feedback Updated");
			return "redirect:feedbackmanager.html";
		} else {
			logger.error("Feedback name Does not exists");
			redirectAttributes.addFlashAttribute("errorMsg","Feedback name Does not exists");
			return "redirect:editfeedback.html?id="+feedback.getId();
		}
	}
	
	@RequestMapping(value = "/studentfeedbacks", method = RequestMethod.GET)
	public String getstudentfeedbacks(
			@ModelAttribute("feedback") @RequestParam("email") String email,
			Feedback feedback, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Feedback Form page ! GET Method : The client locale is {}.", locale + " " + email);

		User user = userService.userExists(email);
		Student_Course student_course = studentCourseService
				.getStudentCourseByUserID(user.getId());
		if (student_course != null) {
			map.put("coursesubjectList", courseSubjectService.getCourseSubjectByCourseId(student_course.getCourse().getId()));
			map.put("feedbackList", feedbackService.feedbackList());
			map.put("feedbackdoneList", feedbackDoneService.getFeedbackDoneByUserId(user.getId()));
		} else {
			logger.error("Given user is not a student");
		}
		return "studentfeedbacks";
	}

	@RequestMapping(value = "/studentfeedbacks", method = RequestMethod.POST)
	public String poststudentfeedbacks(
			@ModelAttribute("feedback") @RequestParam("email") String email,
			@RequestParam("feedback_id") String feedback_id, Feedback feedback,
			Locale locale, Map<String, Object> map) {

		return "redirect:submitfeedback.html?email="
				+ email.substring(0, email.length() - 1) + "&feedback_id="
				+ feedback_id;
	}

	@RequestMapping(value = "/submitfeedback", method = RequestMethod.GET)
	public String getsubmitfeedback(
			@ModelAttribute("answer") Answer answer, @RequestParam("email") String email,
			@RequestParam("feedback_id") Integer feedback_id,
			 Locale locale,  Map<String, Object> map, Model model) {
		
		Feedback newFeedback = feedbackService.getFeedback(feedback_id);
		map.put("questionList", questionService
				.getQuestionByQuestionSetID(newFeedback.getQuestion_set()
						.getId()));
		return "submitfeedback";
	}

	@RequestMapping(value = "/deletefeedback", method = RequestMethod.GET)
	public String getdeletefeedback(@RequestParam("id") Integer id, Locale locale,  final RedirectAttributes redirectAttributes) {
		Feedback feedback = feedbackService.getFeedback(id);
		if(feedback != null) {
			feedbackService.deleteFeedback(feedback.getId());
			logger.info("Feedback deleted successfully");
			redirectAttributes.addFlashAttribute("Msg","Feedback deleted successfully");
			return "redirect:feedbackmanager.html";
		} else {
			logger.error("Feedback doesn't exists");
			redirectAttributes.addFlashAttribute("errorMsg","Feedback doesn't exists");
			return "redirect:feedbackmanager.html";
		}
	}
	
	@RequestMapping(value = "/submitfeedback", method = RequestMethod.POST)
	public String postsubmitfeedback(@ModelAttribute("answer") Answer answer, @RequestParam("email") String email,
			@RequestParam("feedback_id") Integer feedback_id, @RequestParam("n") Integer n, HttpServletRequest req, 
			Locale locale,  final RedirectAttributes redirectAttributes) {
		
		User user = userService.userExists(email);
		Feedback feedback = feedbackService.getFeedback(feedback_id);
		
		Question question;
		String ans;
		String que;
		
		for(int i=1;i<=n;i++) {
			ans = req.getParameter("ans["+i+"]");
			que = req.getParameter("que["+i+"]");
			question = questionService.getQuestion(Integer.parseInt(que));
			answer.setAnswer(Integer.parseInt(ans));
			answer.setFeedback(feedback);
			answer.setUser(user);
			answer.setQuestion(question);
			answerService.addAnswer(answer);
		}
		
		logger.info("Answer added successfully.");
		redirectAttributes.addFlashAttribute("Msg","Feedback Submitted successfully.");
		feedback.setAttempted(feedback.getAttempted()+1);
		feedbackService.editFeedback(feedback);
		Feedback_Done feedback_done = new Feedback_Done();
		feedback_done.setFeedback(feedback);
		feedback_done.setCreated();
		feedback_done.setUser(user);
		feedbackDoneService.addFeedbackDone(feedback_done);
		logger.info("Feedback Done added successfully.");
		
		return "redirect:studentfeedbacks.html?email="+email;
	}
}