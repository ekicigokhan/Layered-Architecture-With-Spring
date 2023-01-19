package kodlama.io.Universty.business.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Messages {

  public class RequestMessages{
    public static final String ADD_DATA_PLEASE = "Please enter a data.";
    public static final String ADD_VALİD_EMAİL_PLEASE = "Please enter a valid e-mail.";
  }

  public class ErrorMessages {
    public static final String ID_NOT_FOUND =
        "The id number entered is unfortunately not available in the database.";
    public static final String ID_NOT_FOUND_OR_NAME_REPEATED =
        "Id not found or name already exists ";
    public static final String CANNOT_BE_BLANK = "This field cannot be left blank";

    public static final String MAİL_DUPLICATED =
            "This email is already defined. Please check it.";
    public static final String BRANCH_NAME_DUPLICATED =
        "The branch registered with this name is already defined.";
    public static final String TEACHER_NAME_DUPLICATED =
        "The teacher registered with this name is already defined.";
    public static final String STUDENT_NAME_DUPLICATED =
        "The student registered with this name is already defined.";
    public static final String DEPARTMENT_NAME_DUPLICATED =
        "The department registered with this name is already defined.";
    public static final String LESSON_NAME_DUPLICATED =
            "The lesson registered with this name is already defined.";
    public static final String FACULTY_NAME_DUPLICATED =
            "The faculty registered with this name is already defined.";
  }

  public class GetListMessages {
    public static final String BRANCHES_LISTED = "Branches listed successfully !";
    public static final String TEACHERS_LISTED = "Teachers listed successfully !";
    public static final String STUDENTS_LISTED = "Students listed successfully !";
    public static final String DEPARTMENTS_LISTED = "Departments listed successfully !";
    public static final String LESSONS_LISTED = "Lessons listed successfully !";
    public static final String FACULTİES_LISTED = "Faculty listed successfully !";
  }

  public class GetByIdMessages {
    public static final String BRANCH_BROUGHT_SUCCESSFULLY =
        "The branch was brought successfully !";
    public static final String TEACHER_BROUGHT_SUCCESSFULLY =
        "The teacher was brought successfully !";
    public static final String STUDENT_BROUGHT_SUCCESSFULLY =
        "The student was brought successfully !";
    public static final String DEPARTMENT_BROUGHT_SUCCESSFULLY =
        "The department was brought successfully !";
    public static final String LESSON_BROUGHT_SUCCESSFULLY =
            "The lesson was brought successfully !";
    public static final String FACULTY_BROUGHT_SUCCESSFULLY =
            "The faculty was brought successfully !";
  }

  public class AddMessages {
    public static final String BRANCH_ADDED = "Branch added successfully !";
    public static final String TEACHER_ADDED = "Teacher added successfully !";
    public static final String STUDENT_ADDED = "Student added successfully !";
    public static final String DEPARTMENT_ADDED = "Department added successfully !";
    public static final String LESSON_ADDED = "Lesson added successfully !";
    public static final String FACULTY_ADDED = "Faculty added successfully !";
  }

  public class DeleteMessages {
    public static final String BRANCH_DELETED = "Branch removed successfully !";
    public static final String TEACHER_DELETED = "Teacher removed successfully !";
    public static final String STUDENT_DELETED = "Student removed successfully !";
    public static final String DEPARTMENT_DELETED = "Department removed successfully !";
    public static final String LESSON_DELETED = "Lesson removed successfully !";
    public static final String FACULTY_DELETED = "Faculty removed successfully !";
  }

  public class UpdateMessages {
    public static final String BRANCH_UPDATED = "Branch updated successfully !";
    public static final String TEACHER_UPDATED = "Teacher updated successfully !";
    public static final String STUDENT_UPDATED = "Student updated successfully !";
    public static final String DEPARTMENT_UPDATED = "Department updated successfully !";
    public static final String LESSON_UPDATED = "Lesson updated successfully !";
    public static final String FACULTY_UPDATED = "Faculty updated successfully !";
  }

  public class LogMessages {}
}
