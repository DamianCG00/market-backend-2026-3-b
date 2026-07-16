package mx.edu.tecdesoftware.market_backend_2026_3_b.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    @Operation(summary = "Get Purche by ID client",
            description = "Return all product/Purche(s) by in specific ClienteID")

    @ApiResponse(responseCode = "200",
            description = "Purche(s) found in the IDClient")

    @ApiResponse(responseCode = "404",
            description = "Purche(s) NOT found in the IDClient")

    @ApiResponse(responseCode = "500",
            description = "Internal server Error")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    @Operation(
            summary = "Find a Client`s purchase by ClientID",
            description = "Register a new product and retorn it",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value =
                                            """
                                            {
                                              "purchaseId": 0,
                                              "clientId": "99999",
                                              "date": "Hoy mismo",
                                              "paymentMethod": "T",
                                              "comment": "Varios tipos de helado",
                                           
                                              "productId": 1,
                                              "quantity": 200,
                                              "total": 1500,
                                              "active": true
                                      
                                            }
                                            """
                            )
                    )
            )
    )


    @ApiResponse(responseCode = "200",
            description = "Product(s) found in the category")

    @ApiResponse(responseCode = "404",
            description = "Product(s) NOT found in the category")

    @ApiResponse(responseCode = "500",
            description = "Internal server Error")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("id") String clientId) {
        return purchaseService.getByClient(clientId)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Get Save All purches by ClienteID",
            description = "Return all product by in specifici category")

    @ApiResponse(responseCode = "200",
            description = "Product(s) found in the category")

    @ApiResponse(responseCode = "404",
            description = "Product(s) NOT found in the category")

    @ApiResponse(responseCode = "500",
            description = "Internal server Error")

    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}