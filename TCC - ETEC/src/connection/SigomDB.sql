/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Sindicato
 * Created: 09/06/2023
 */

create table fornecedor(
    idFornecedor serial primary key not null,
    nomeFornecedor varchar(20) not null,
    cnpjFornecedor varchar(18) not null,
    telefoneFornecedor varchar(20) not null
);

create table Usuario(
	idUsusuario serial not null primary key,
    nomeUsuario VARCHAR(100) not null,
    cargoUsuario VARCHAR(100) not null,
    emailUsuario VARCHAR(100) not null,
    senhaUsuario VARCHAR(30) not null
);

create table veiculo(
    idVeiculo serial primary key not null,
    modelo varchar(50) not null,
    marca varchar(50) not null,
    cor varchar(50) not null,
    cidadeVeiculo varchar(50) not null,
    placa varchar(8) not null,
    anoDeFabricacao varchar(50) not null,
    chassi varchar(17) not null,
    renavam varchar(9) not null,
    numeroDoMotor varchar(9) not null,
    quilometragemVeiculo varchar(7) not null,
    tipoCombustivel varchar(50) not null
);

create table cliente(
    idCliente serial primary key not null,
    nomeCliente varchar(50) not null,
    dataDeNascimentoCliente varchar(10) not null,
    cpfCliente varchar(14) unique not null,
    cepCliente varchar(9) not null,
	
    enderecoCliente varchar(100) not null,
    cidadeCliente varchar(50) not null,
    bairroCliente varchar(50) not null,
    estadoCliente varchar(50) not null,
    celularCliente varchar(15) not null,
    emailCliente varchar(50) not null
);