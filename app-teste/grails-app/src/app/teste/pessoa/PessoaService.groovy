package app.teste.pessoa

import grails.gorm.services.Service

@Service(Pessoa)
interface PessoaService {

    Pessoa get(Serializable id)

    List<Pessoa> list(Map args)

    Long count()

    void delete(Serializable id)

    Pessoa save(Pessoa pessoa)

}