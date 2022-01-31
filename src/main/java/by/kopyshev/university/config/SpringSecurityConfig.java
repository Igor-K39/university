package by.kopyshev.university.config;

import by.kopyshev.university.service.UserService;
import by.kopyshev.university.web.controller.person.PersonRestController;
import by.kopyshev.university.web.controller.education.educator.EducatorRestController;
import by.kopyshev.university.web.controller.education.lecture.AdminLectureRestController;
import by.kopyshev.university.web.controller.education.student.StudentRestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import static by.kopyshev.university.web.controller.building.CampusRestController.CAMPUS_REST_URL;
import static by.kopyshev.university.web.controller.building.LectureHallRestController.LECTURE_HALL_REST_URL;
import static by.kopyshev.university.web.controller.education.discipline.DisciplineRestController.DISCIPLINE_REST_URL;
import static by.kopyshev.university.web.controller.education.educator.PreviewEducatorRestController.PREVIEW_EDUCATOR_REST_URL;
import static by.kopyshev.university.web.controller.education.faculty.FacultyDepartmentRestController.FACULTY_DEPARTMENT_REST_URL;
import static by.kopyshev.university.web.controller.education.faculty.FacultyRestController.FACULTY_REST_URL;
import static by.kopyshev.university.web.controller.education.group.StudentGroupRestController.STUDENT_GROUP_REST_URL;
import static by.kopyshev.university.web.controller.education.lecture.LectureRestController.LECTURE_REST_URL;
import static by.kopyshev.university.web.controller.education.speciality.SpecialityRestController.SPECIALITY_REST_URL;
import static by.kopyshev.university.web.controller.education.student.PreviewStudentRestController.PREVIEW_STUDENT_REST_URL;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService service;

    private final PasswordEncoder passwordEncoder;

    public SpringSecurityConfig(UserService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .eraseCredentials(true)
                .userDetailsService(service).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] guestApi = new String[]{
                CAMPUS_REST_URL + "**",
                LECTURE_HALL_REST_URL + "**",
                DISCIPLINE_REST_URL + "/**",
                PREVIEW_EDUCATOR_REST_URL + "/**",
                FACULTY_REST_URL + "**",
                FACULTY_DEPARTMENT_REST_URL + "/**",
                SPECIALITY_REST_URL + "**"
        };

        String[] authenticatedApi = new String[] {
                STUDENT_GROUP_REST_URL + "**",
                LECTURE_REST_URL + "/**",
                PREVIEW_STUDENT_REST_URL + "/**"
        };

        String[] dispatcherApi = new String[] {
                EducatorRestController.EDUCATOR_REST_URL + "/**",
                AdminLectureRestController.ADMIN_LECTURE_REST_URL + "/**",
                StudentRestController.STUDENT_REST_URL + "**",
                PersonRestController.PERSON_REST_URL + "**"
        };

        http
                .authorizeRequests()
                .antMatchers("/swagger-ui/", "/swagger-ui/**", "/swagger-resources/**", "/v2/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .and().authorizeRequests().antMatchers(guestApi).permitAll()
                .and().authorizeRequests().antMatchers(authenticatedApi).hasAnyRole("STUDENT", "DISPATCHER", "ADMIN")
                .and().authorizeRequests().antMatchers(dispatcherApi).hasAnyRole("DISPATCHER", "ADMIN")
                .and().authorizeRequests().antMatchers("/api/admin/**").hasRole("ADMIN")
                .and()
                .httpBasic();

        http.csrf().disable().cors().disable();
        http.headers().frameOptions().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
