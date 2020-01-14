namespace java com.yoje.traces.vs.service

struct FinagleRequest {
    1: string param
}

struct FinagleResponse {
    1: string resp
}

service AlphaService {

    FinagleResponse invoke(FinagleRequest request);
}

service BetaService {

    FinagleResponse invoke(FinagleRequest request);
}