package app.adminlte


class HomeInterceptor {


    HomeInterceptor() {
        matchAll()
                .excludes(controller: 'login')
                .excludes(controller: 'auth')
                .excludes(uri: '/static/**')
    }

    boolean before() {

       return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
