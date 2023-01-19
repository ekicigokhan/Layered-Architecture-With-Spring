package kodlama.io.Universty.business.concretes;

import kodlama.io.Universty.business.abstracts.FacultyService;
import kodlama.io.Universty.business.constants.Messages;
import kodlama.io.Universty.core.utilities.customExceptions.BusinessException;
import kodlama.io.Universty.core.utilities.results.DataResult;
import kodlama.io.Universty.core.utilities.results.Result;
import kodlama.io.Universty.core.utilities.results.SuccessDataResult;
import kodlama.io.Universty.core.utilities.results.SuccessResult;
import kodlama.io.Universty.dataAccess.abstracts.FacultyRepository;
import kodlama.io.Universty.entities.concretes.Faculty;
import kodlama.io.Universty.webApi.model.requests.faculty.FacultyAddRequest;
import kodlama.io.Universty.webApi.model.requests.faculty.FacultyUpdateRequest;
import kodlama.io.Universty.webApi.model.responses.faculty.GetAllFacultyResponse;
import kodlama.io.Universty.webApi.model.responses.faculty.GetByIdFacultyResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FacultyManager implements FacultyService {

    private FacultyRepository facultyRepository;

    public FacultyManager(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public DataResult<List<GetAllFacultyResponse>> getAll() {
        List<Faculty> faculties = facultyRepository.findAll();
        List<GetAllFacultyResponse> facultyResponseList = new ArrayList<>();
        for (Faculty inDbFaculty : faculties) {
            GetAllFacultyResponse getAllFacultyResponse = GetAllFacultyResponse.builder().name(inDbFaculty.getName()).id(inDbFaculty.getId()).build();
            facultyResponseList.add(getAllFacultyResponse);
        }
        return new SuccessDataResult<>(facultyResponseList, Messages.GetListMessages.FACULTİES_LISTED);
    }

    @Override
    public DataResult<GetByIdFacultyResponse> getById(int id) throws Exception {
        Faculty inDbFaculty = facultyRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
        GetByIdFacultyResponse getByIdFacultyResponse = GetByIdFacultyResponse.builder().name(inDbFaculty.getName()).departments(inDbFaculty.getDepartments()).build();
        return new SuccessDataResult<>(getByIdFacultyResponse + " " + getByIdFacultyResponse.getName());
    }

    @Override
    public Result add(FacultyAddRequest facultyAddRequest) throws Exception {
        existsByFacultyName(facultyAddRequest.getName());
        Faculty faculty = Faculty.builder().name(facultyAddRequest.getName()).build();
        facultyRepository.save(faculty);
        return new SuccessResult(Messages.AddMessages.FACULTY_ADDED + " " + facultyAddRequest.getName());
    }

    @Override
    public Result update(int id, FacultyUpdateRequest facultyUpdateRequest) throws Exception {
        existsByFacultyName(facultyUpdateRequest.getName());
        Faculty inDbFaculty = facultyRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
        Faculty faculty = Faculty.builder().name(facultyUpdateRequest.getName()).build();
        faculty.setId(inDbFaculty.getId());
        facultyRepository.save(faculty);
        return new SuccessResult(Messages.UpdateMessages.FACULTY_UPDATED + " " + facultyUpdateRequest.getName());
    }

    @Override
    public Result delete(int id) throws Exception {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
        facultyRepository.deleteById(id);
        return new SuccessResult(Messages.DeleteMessages.FACULTY_DELETED+ " " + faculty.getName());
    }

    @Override
    public Faculty getFacultyById(int id) {
        return facultyRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND));
    }

    @Override
    public void isFacultyExists(int id) {
        if (!facultyRepository.existsById(id)) {
            throw new BusinessException(Messages.ErrorMessages.ID_NOT_FOUND);
        }
    }

    private void existsByFacultyName(String name) {
        if (facultyRepository.existsByName(name)) {
            throw new BusinessException(Messages.ErrorMessages.FACULTY_NAME_DUPLICATED);
        }
    }
}
