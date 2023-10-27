package com.example.rent.it.integration;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "Pagamento",
       url = "https://api-sandbox.getnet.com.br/5a1d8050-212a-410b-892e-ded5461b917d/credito")
public interface GetNet {
    


}
