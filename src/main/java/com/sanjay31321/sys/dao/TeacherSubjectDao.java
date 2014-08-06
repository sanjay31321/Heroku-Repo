package com.sanjay31321.sys.dao;

import java.util.List;

import com.sanjay31321.sys.model.Teacher_Subject;

public interface TeacherSubjectDao {
	public List<Teacher_Subject> teacherSubjectList();
	public void addTeacherSubject(Teacher_Subject teacher_subject);
	public void editTeacherSubject(Teacher_Subject teacher_subject);
	public void deleteTeacherSubject(int id);
	public Teacher_Subject getTeacherSubject(int id);
	public List<Teacher_Subject> getTeacherSubjectByTeacherId(int teacher_id);
	public Teacher_Subject subjectExistsWithTeacher(int teacher_id, int subject_id);
}
