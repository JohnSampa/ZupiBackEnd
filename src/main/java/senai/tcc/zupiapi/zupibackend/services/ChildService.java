package senai.tcc.zupiapi.zupibackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senai.tcc.zupiapi.zupibackend.dto.ChildDTO;
import senai.tcc.zupiapi.zupibackend.dto.ChildResponseDTO;
import senai.tcc.zupiapi.zupibackend.model.Child;
import senai.tcc.zupiapi.zupibackend.repositories.ChildRepository;
import senai.tcc.zupiapi.zupibackend.repositories.UserRepository;

import java.util.List;

@Service
public class ChildService {

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Child> findAll() {
        return childRepository.findAll();
    }

    public Child findById(Integer id) {
        return childRepository.findById(id).orElse(null);
    }

    public ChildResponseDTO save(ChildDTO child) {
        Child childEntity = new Child();
        childEntity.setName(child.name());
        childEntity.setBirthDate(child.birthDate());
        childEntity.setSchoolClass(child.schoolClass());
        childEntity.setCondition(child.condition());
        childEntity.setResponsible(userRepository.findById(child.responsibleId()).orElse(null));

        return new ChildResponseDTO(childRepository.save(childEntity));
    }

    public List<ChildResponseDTO> findByResponsibleId(Long id) {
        List<Child> list = childRepository.findByResponsibleId(id);

        return list.stream().map(ChildResponseDTO::new).toList();
    }
}
