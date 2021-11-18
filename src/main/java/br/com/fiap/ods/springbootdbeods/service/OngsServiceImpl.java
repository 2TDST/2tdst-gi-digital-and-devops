package br.com.fiap.ods.springbootdbeods.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.ods.springbootdbeods.model.Ongs;
import br.com.fiap.ods.springbootdbeods.repository.OngsRepository;

@Service
public class OngsServiceImpl implements OngsService {

    @Autowired
    private OngsRepository ongsRepository;

    @Override
    public List < Ongs > getAllOngs() {
        return ongsRepository.findAll();
    }

    @Override
    public void saveOngs(Ongs ongs) {
        this.ongsRepository.save(ongs);
    }

    @Override
    public Ongs getOngsById(long id) {
        Optional < Ongs > optional = ongsRepository.findById(id);
        Ongs ongs = null;
        if (optional.isPresent()) {
            ongs = optional.get();
        } else {
            throw new RuntimeException(" Ong not found for id :: " + id);
        }
        return ongs;
    }

    @Override
    public void deleteOngsById(long id) {
        this.ongsRepository.deleteById(id);
    }

}
