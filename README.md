# orderms
aplicação usando spring, rabbitMQ e mongoDB simulando um serviço de publicação de ordem de serviços

* Java JDK 21
* MongoDB: 7.0.11

# Detalhes

Entrar na pasta local com o comando: `cd ./local` e executar o comando: `docker compose up`. Isso deve criar as imagens do RabbitMQ e MongoDB.

obs: Eu usei o MongoDB [Compass](https://www.mongodb.com/products/tools/compass) para conectar com o banco. Ele tem uma interface bem amigável.

Para acessar o rabbitMQ acessa a porta: `http://localhost:15672/` e passar `guest` como username e password.

* aqui é possível enviar um evento no payload para a [fila](http://localhost:15672/#/queues/%2F/order-created), por exemplo:

  ```
   {
       "codigoPedido": 1001,
       "codigoCliente":1,
       "itens": [
           {
               "produto": "iphone",
               "quantidade": 2,
               "preco": 10.0
           },
           {
               "produto": "carro",
               "quantidade": 5,
               "preco": 100000.0
           }
       ]
   }
  ```

No MongoDB Compass você pode checar se o evento foi consumido.

É possível buscar o total de todos os pedidos por cliente: através da [url](http://localhost:8081/customers/{customerId}/orders).

  




