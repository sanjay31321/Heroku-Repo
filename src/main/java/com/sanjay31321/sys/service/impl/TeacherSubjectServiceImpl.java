package com.sanjay31321.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjay31321.sys.dao.TeacherSubjectDao;
import com.sanjay31321.sys.model.Teacher_Subject;
import com.sanjay31321.sys.service.TeacherSubjectService;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService{
	
	@Autowired
	private TeacherSubjectDao teacherSubjectDao;

	@Transactional
	public List<Teacher_Subject> teacherSubjectList() {
		return teacherSubjectDao.teacherSubjectList();
	}

	@Transactional
	public void addTeacherSubject(Teacher_Subject teacher_subject) {
		teacherSubjectDao.addTeacherSubject(teacher_subject);
	}

	@Transactional
	public void editTeacherSubject(Teacher_Subject teacher_subject) {
		teacherSubjectDao.editTeacherSubject(teacher_subject);
	}

	@Transactional
	public void deleteTeacherSubject(int id) {
		teacherSubjectDao.deleteTeacherSubject(id);
	}

	@Transactional
	public Teacher_Subject getTeacherSubject(int id) {
		return teacherSubjectDao.getTeacherSubject(id);
	}

	@Transactional
	public List<Teacher_Subject> getTeacherSubjectByTeacherId(int teacher_id) {
		return teacherSubjectDao.getTeacherSubjectByTeacherId(teacher_id);
	}

	@Transactional
	public Teacher_Subject subjectExistsWithTeacher(int teacher_id, int subject_id) {
		return teacherSubjectDao.subjectExistsWithTeacher(teacher_id, subject_id);
	}

	@Transactional
	public boolean subjectExistsWithTeacherID(int teacher_id, int subject_id) {
		Teacher_Subject teacher_subject = teacherSubjectDao.subjectExistsWithTeacher(teacher_id, subject_id);
		
		if(teacher_subject == null) return false;
			else return true;
	}
}
