package logic;

import service.Service;
import shared.LectureDTO;
import shared.ReviewDTO;

import javax.security.auth.callback.Callback;
import java.util.ArrayList;
import dal.MYSQLDriver;
import service.DBWrapper;
import shared.CourseDTO;
import shared.LectureDTO;
import shared.ReviewDTO;
import shared.UserDTO;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserController {


    public UserController(){
    }


    public ArrayList<LectureDTO> getLectures(int courseId){

        ArrayList<LectureDTO> lectures = new ArrayList<LectureDTO>();

        try {
            Map<String, String> params = new HashMap();

            params.put("id", String.valueOf(courseId));


            ResultSet rs = DBWrapper.getRecords("lecture", null, params, null, 0);


            while (rs.next()){
                LectureDTO lecture = new LectureDTO();

                lecture.setType(rs.getString("type"));
                lecture.setDescription(rs.getString("description"));
                lectures.add(lecture);
            }


            } catch (SQLException e){

            }
        return lectures;
    }

    public ArrayList<CourseDTO> getCourses(int userId){

        ArrayList<CourseDTO> courses = new ArrayList<CourseDTO>();

        try {
            Map<String, String> params = new HashMap();
            Map<String, String> joins = new HashMap();

            params.put("id", String.valueOf(userId));
            joins.put("table","course_attendant");

            String[] attributes = new String[]{"name", "code"};
            ResultSet rs = DBWrapper.getRecords("course", attributes, params, null, 0);


            while (rs.next()){
                CourseDTO course = new CourseDTO();

                course.setName(rs.getString("name"));
                course.setId(rs.getString("code"));
                courses.add(course);
            }


        } catch (SQLException e){

        }
        return courses;
    }

}
