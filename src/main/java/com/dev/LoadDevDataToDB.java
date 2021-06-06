package com.dev;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import com.constants.SemesterNo;
import com.constants.Sex;
import com.models.AcademicManager;
import com.models.Class;
import com.models.Course;
import com.models.CourseRegistration;
import com.models.Semester;
import com.models.ShiftTime;
import com.models.Student;
import com.models.Subject;
import com.services.AcademicManagerServices;
import com.services.ClassServices;
import com.services.CourseRegistrationServices;
import com.services.CourseServices;
import com.services.SemesterServices;
import com.services.ShiftTimeServices;
import com.services.StudentServices;
import com.services.SubjectServices;

public class LoadDevDataToDB {

    public static void loadAllDataDev() {
        try {
            loadClassData();
            loadStudent();
            loadAcademicManager();
            loadShiftTimeData();
            loadSubjectData();
            loadSemester();
            loadCourseData();
            setCurrentSemester();
            setCourseRegistration();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void loadClassData() throws IOException {
        File file = new File("./src/main/java/com/dev/ClassData.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        ClassServices services = new ClassServices();
        Class classObj = new Class();

        try {
            while (true) {
                classObj.setClassName(reader.readLine());

                String line = reader.readLine();
                if (line == null)
                    break;
                if (line.length() == 0) {
                    services.createClass(classObj);
                    continue;
                }
            }
        } catch (EOFException e) {
            reader.close();
        }
    }

    private static void loadStudent() throws IOException, ParseException {
        File file = new File("./src/main/java/com/dev/StudentData.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        StudentServices services = new StudentServices();
        Student student = new Student();

        List<Class> allClasses = (new ClassServices()).getClass(1, 10);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        while (true) {
            student.setUsername(reader.readLine());
            student.setPassword(reader.readLine());
            student.setFirstName(reader.readLine());
            student.setLastName(reader.readLine());
            student.setAddress(reader.readLine());
            student.setBirth(formatter.parse(reader.readLine()));

            String sex = reader.readLine();
            switch (sex) {
                case "0":
                    student.setSex(Sex.MALE);
                    break;
                case "1":
                    student.setSex(Sex.FEMALE);
                    break;
                default:
            }
            student.setStartYear(Integer.parseInt(reader.readLine()));
            student.setStudentClass(randEleInList(allClasses));

            String line = reader.readLine();
            if (line == null)
                break;
            if (line.length() == 0) {
                services.createStudent(student);
            }
        }

        reader.close();
    }

    private static void loadAcademicManager() throws IOException, ParseException {
        File file = new File("./src/main/java/com/dev/AcademicManager.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        AcademicManagerServices services = new AcademicManagerServices();
        AcademicManager academicManager = new AcademicManager();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        while (true) {
            academicManager.setUsername(reader.readLine());
            academicManager.setPassword(reader.readLine());
            academicManager.setFirstName(reader.readLine());
            academicManager.setLastName(reader.readLine());
            academicManager.setAddress(reader.readLine());
            academicManager.setBirth(formatter.parse(reader.readLine()));

            String sex = reader.readLine();
            switch (sex) {
                case "0":
                    academicManager.setSex(Sex.MALE);
                    break;
                case "1":
                    academicManager.setSex(Sex.FEMALE);
                    break;
                default:
            }

            String line = reader.readLine();
            if (line == null)
                break;
            if (line.length() == 0) {
                services.createAcademicManager(academicManager);
            }
        }

        reader.close();
    }

    private static void loadSemester() throws IOException, ParseException {
        File file = new File("./src/main/java/com/dev/SemesterData.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        SemesterServices services = new SemesterServices();
        Semester semester = new Semester();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        while (true) {

            String semNo = reader.readLine();
            switch (semNo) {
                case "1":
                    semester.setSemNo(SemesterNo.SEM1);
                    break;
                case "2":
                    semester.setSemNo(SemesterNo.SEM2);
                    break;
                default:
                    semester.setSemNo(SemesterNo.SEM3);
            }

            semester.setStartDate(formatter.parse(reader.readLine()));
            semester.setEndDate(formatter.parse(reader.readLine()));

            String line = reader.readLine();
            if (line == null)
                break;
            if (line.length() == 0) {
                services.createSemester(semester);
            }
        }

        reader.close();
    }

    private static void loadSubjectData() throws IOException {
        File file = new File("./src/main/java/com/dev/SubjectData.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        SubjectServices services = new SubjectServices();
        Subject subject = new Subject();

        while (true) {
            subject.setSubjectCode(reader.readLine());
            subject.setSubjectName(reader.readLine());
            subject.setCredits(Integer.parseInt(reader.readLine()));

            String line = reader.readLine();
            if (line == null)
                break;
            if (line.length() == 0) {
                services.createSubject(subject);
            }
        }

        reader.close();
    }

    private static void loadCourseData() throws IOException {
        File file = new File("./src/main/java/com/dev/CourseData.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        CourseServices services = new CourseServices();
        Course course = new Course();

        List<Subject> subjects = (new SubjectServices()).getSubjects(1, 10);
        List<ShiftTime> shifts = (new ShiftTimeServices()).getShiftTImes(1, 10);

        int rand = (int) (Math.random() + 7);
        while (true) {
            course.setTeacherName(reader.readLine());
            course.setDepartmentName(reader.readLine());
            course.setNumberOfSlot(Integer.parseInt(reader.readLine()));
            course.setSubject(randEleInList(subjects));
            course.setDayOfWeek(DayOfWeek.of(rand));
            course.setShiftTime(randEleInList(shifts));

            String line = reader.readLine();
            if (line == null)
                break;
            if (line.length() == 0) {
                services.createCourse(course);
            }
        }

        reader.close();
    }

    private static void loadShiftTimeData() throws IOException, ParseException {
        File file = new File("./src/main/java/com/dev/ShiftTimeData.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        ShiftTimeServices services = new ShiftTimeServices();
        ShiftTime shiftTime = new ShiftTime();

        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");

        while (true) {
            shiftTime.setStartTime(formatter.parse(reader.readLine()));
            shiftTime.setEndTime(formatter.parse(reader.readLine()));

            String line = reader.readLine();
            if (line == null)
                break;
            if (line.length() == 0) {
                services.createShiftTime(shiftTime);
            }
        }

        reader.close();
    }

    static private void setCurrentSemester() {
        SemesterServices services = new SemesterServices();
        services.setCurrentSemester(services.getSemesters(1, 1).get(0));
    }

    static private <T> T randEleInList(List<T> list) {
        int rand = (int) (Math.random() * list.size());
        return list.get(rand);
    }

    static private void setCourseRegistration() {
        CourseRegistrationServices services = new CourseRegistrationServices();

        List<Course> allCourses = (new CourseServices()).getCourses(1, 10);
        List<Student> allStudents = (new StudentServices()).getStudents(1, 10);

        for (int i = 1; i < 20; i++) {
            Student randStu = randEleInList(allStudents);
            Course randCour = randEleInList(allCourses);
            services.createCourseRegistration(
                    CourseRegistration.builder().course(randCour).student(randStu).build());
        }
    }
}
