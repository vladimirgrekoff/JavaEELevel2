package com.grekoff.lesson4.endpoints;

import com.grekoff.lesson4.services.ProductsService;
import com.grekoff.lesson4.soap.products.GetAllProductsRequest;
import com.grekoff.lesson4.soap.products.GetAllProductsResponse;
import com.grekoff.lesson4.soap.products.GetProductByTitleRequest;
import com.grekoff.lesson4.soap.products.GetProductByTitleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductsEndpoint {
    private static final String NAMESPACE_URI = "http://www.grekoff.com/lesson4/products";
    private final ProductsService productsService;

    /*
        Пример запроса: POST http://localhost:8189/lesson4/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.grekoff.com/lesson4/products">
             <soapenv:Header/>
             <soapenv:Body>
                 <f:getProductByTitleRequest>
                     <f:title>Масло</f:title>
                 </f:getProductByTitleRequest>
             </soapenv:Body>
         </soapenv:Envelope>
    */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByTitleRequest")
    @ResponsePayload
    public GetProductByTitleResponse getProductByTitle(@RequestPayload GetProductByTitleRequest request) {
        GetProductByTitleResponse response = new GetProductByTitleResponse();
        response.setProduct(productsService.getByTitle(request.getTitle()));
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8189/lesson4/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.grekoff.com/lesson4/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productsService.getAllStudents().forEach(response.getProducts()::add);
        return response;
    }
}

