package com.grekoff.lesson4.endpoints;

import com.grekoff.lesson4.services.CategoryService;
import com.grekoff.lesson4.soap.categories.GetCategoryByTitleRequest;
import com.grekoff.lesson4.soap.categories.GetCategoryByTitleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
@RequiredArgsConstructor
public class CategoryEndpoint {

    private static final String NAMESPACE_URI = "http://www.grekoff.com/lesson4/categories";
    private final CategoryService categoryService;

    /*
        Пример запроса: POST http://localhost:8189/lesson4/lesson4/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.grekoff.com/lesson4/categories">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getCategoryByTitleRequest>
                    <f:title>Бытовая химия</f:title>
                </f:getCategoryByTitleRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoryByTitleRequest")
    @ResponsePayload
    @Transactional
    public GetCategoryByTitleResponse getCategoryByTitle(@RequestPayload GetCategoryByTitleRequest request) {
        GetCategoryByTitleResponse response = new GetCategoryByTitleResponse();
        response.setCategory(categoryService.getByTitle(request.getTitle()));
        return response;
    }

}
