# transaction-simulator

[![LinkedIn][linkedin-shield]][linkedin-url]

Este projeto é uma API de Transações que pode ser utilizada como um Mock

Essa API foi desenvolvida com a premissa de se utilizar o minimo de Libs, focando
na lógica de desenvolvimento da geração de transações aleatórias e tratamento de erros 
na validação dos parametros recebidos da requisição.

## Tecnologias
![java-shield] ![spring-boot-shield] ![junit-shield]

## Requisição

:cloud: *Pode levar alguns segundos para que a API inicie no heroku*

[![heroku-shield]](https://autorization-simulator.herokuapp.com/1000/transacoes/2021/12)


**Resposta**
```yaml
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
**Resposta com tratamento de erro**
```yaml
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


[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[java-shield]: https://badgen.net/badge/Java/1.8/blue
[spring-boot-shield]: https://badgen.net/badge/Spring%20Boot/2.4.4/blue
[junit-shield]: https://badgen.net/badge/JUnit/5/blue
[heroku-shield]: https://img.shields.io/badge/Heroku-430098?style=for-the-badge&logo=heroku&logoColor=white

[linkedin-url]: https://www.linkedin.com/in/márcio-césar-5bb658104/
[heroku-url]: https://autorization-simulator.herokuapp.com/1000/transacoes/2021/12
