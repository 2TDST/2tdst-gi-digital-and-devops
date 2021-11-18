package br.com.fiap.ods.springbootdbeods.service;

import java.util.List;
import br.com.fiap.ods.springbootdbeods.model.Ongs;
import org.springframework.data.domain.Page;

public interface OngsService {
    List < Ongs > getAllOngs();
    void saveOngs(Ongs ongs);
    Ongs getOngsById(long id);
    void deleteOngsById(long id);
    Page<Ongs> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
