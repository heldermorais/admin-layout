package app.teste.cidade

import grails.gorm.services.Service

@Service(Cidade)
interface CidadeService {

    Cidade get(Serializable id)

    List<Cidade> list(Map args)

    Long count()

    void delete(Serializable id)

    Cidade save(Cidade cidade)

}