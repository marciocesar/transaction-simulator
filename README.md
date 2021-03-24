# transaction-simulator
Este projeto é uma API de Transações que pode ser utilizada como um Mock

Essa API foi desenvolvida com a premissa de se utilizar o minimo de Libs, focando
na lógica de desenvolvimento da geração de transações aleatórias e tratamento de erros 
na validação dos parametros recebidos da requisição.

**Contrato**
```
200 OK

[GET] /<id>/transacoes/<ano>/<mes>

Content-type: application/json

[
  {
     "descricao": "string"
     "data": "long"
     "valor": "integer"
  }  
]
```
**Tratamento de erro**
```
400 Bad Request

Content-type: application/json

{
    "status": "String",
    "message": "String",
    "dateTime": "24-03-2021T05:12:19",
    "errors": [
        {
            "field": "String",
            "rejectedValue": Integer,
            "message": "String"
        }
    ]
}
```
