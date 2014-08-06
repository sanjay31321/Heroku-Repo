package com.sanjay31321.sys.service;

import java.util.List;

import com.sanjay31321.sys.model.Teacher_Subject;

public interface TeacherSubjectService {
	public List<Teacher_Subject> teacherSubjectList();
	public void addTeacherSubject(Teacher_Subject teacher_subject);
	public void editTeacherSubject(Teacher_Subject teacher_subject);
	public void deleteTeacherSubject(int id);
	public Teacher_Subject getTeacherSubject(int id);
	public List<Teacher_Subject> getTeacherSubjectByTeacherId(int teacher_id);
	public Teacher_Subject subjectExistsWithTeacher(int teacher_id, int subject_id);
	public boolean subjectExistsWithTeacherID(int teacher_id, int subject_id);
}
