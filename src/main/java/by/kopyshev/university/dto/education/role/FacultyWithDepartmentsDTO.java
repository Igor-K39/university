package by.kopyshev.university.dto.education.role;

import by.kopyshev.university.dto.education.FacultyDTO;
import by.kopyshev.university.dto.education.FacultyDepartmentDTO;

import java.util.List;

public class FacultyWithDepartmentsDTO extends FacultyDTO {

    private List<FacultyDepartmentDTO> facultyDepartmentDTOs;

    public FacultyWithDepartmentsDTO() {
    }

    public FacultyWithDepartmentsDTO(Integer id, String name, String deanAddress,
                                     String deanEmail, String deanPhoneNumber, String description,
                                     List<FacultyDepartmentDTO> facultyDepartmentDTOs) {
        super(id, name, deanAddress, deanEmail, deanPhoneNumber, description);
        this.facultyDepartmentDTOs = facultyDepartmentDTOs;
    }

    public List<FacultyDepartmentDTO> getFacultyDepartments() {
        return facultyDepartmentDTOs;
    }

    public void setFacultyDepartments(List<FacultyDepartmentDTO> facultyDepartmentDTOs) {
        this.facultyDepartmentDTOs = facultyDepartmentDTOs;
    }
}
