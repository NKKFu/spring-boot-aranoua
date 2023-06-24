# Projeto de Aprendizagem do Curso de Spring e Testes

Considerando a descrição abaixo, desenvolva as classes de modelo (model classes), classes Repository e Controller,
utilizando o Framework Spring, e as respectivas classes de teste em Java para atender às necessidades do negócio.

## Cadastro para Ajuda Humanitária da MSF – Médico Sem Fronteiras

A organização “Médico Sem Fronteiras” (MSF) tem apoiado a saúde pública nos país que estão passando por crises
decorrente de guerras e outras situações políticas adversas. Essa organização apoia através do fornecimento de
estrutura para atendimento médico, com hospitais de campanha e profissionais de saúde voluntários (p. ex. médicos e
enfermeiros).

O profissional de saúde interessado em participar desse programa deve se cadastrar primeiramente no site da MSF.
Quando ocorre uma solicitação de ajuda internacional, os profissionais são enviados até determinada cidade de um
país, e realizam o atendimento médico de forma voluntária na população local. Para realizar o cadastro de interessado,
é preciso fornecer alguns dados e atender alguns critérios.

Regras de Negócio:
 - RN-01: O voluntário deve ter os seguintes dados cadastrados (todos obrigatórios): Passaporte, Nome Completo, Idade, Telefone, E-mail e Tipo Sanguíneo.
 - RN-02: Um voluntário não pode ser cadastrado se já tiver um passaporte com o mesmo número no sistema.
 - RN-03: O voluntário deve ter de 18 a 55 anos de idade;
 - RN-04: O voluntário deve estar associado a uma cidade de um determinado país.
 - RN-05: O voluntário deve ter sua situação de saúde declarada (Opções: Ruim, Bom, Ótimo).
> Todas as regras foram implementadas

Para simplificar a modelagem de dados, considera as seguintes entidades no sistema: Voluntário, Situação de Saúde, Cidade e País.

Instruções:
1. Implementar as classes Model, Repository e Controller, utilizando o Framework Spring (Spring Boot, Spring
   MVC e Spring Data JPA), conforme a descrição acima e as regras escolhidas.
> A Situação de Saúde não é um modelo, mas sim um enum dentro do sistema.

2. Implementar as respectivas classes de testes para as classes acima, utilizando os frameworks JUnit e Spring,
   de modo que atenda as regras escolhidas:
   a. Considere para cada regra, pelo menos, 2 cenários : 1 positivo e 1 negativo.
   b. Considere, pelo menos, uma classe de teste implementada para cada classe: Model, Repository,
   Controller.
> Testes desenvolvidos e testados um à um
3. Executar as classes de teste e registrar as evidências do teste automatizado (print de tela ou relatório).
> Disponível como imagem no arquivo: testes.png na pasta raiz

## Testes executados:

### Voluntario
 - Testes de Repository
    - 1 Positivo / 1 Negativo
 - Testes de Controller
    - 1 Positivo / 1 Negativo

### Cidade
 - Testes de Repository
    - 1 Positivo / 1 Negativo
 - Testes de Controller
    - 1 Positivo / 1 Negativo

### Pais
 - Testes de Repository
    - 1 Positivo / 1 Negativo
 - Testes de Controller
    - 1 Positivo / 1 Negativo

## Misc
Use os dados abaixo para criar um novo voluntário
```json
{
    "passaporteId": "AA0001",
    "nomeCompleto": "Nelson Filho",
    "idade": 18,
    "telefone": "+55 98888-4444",
    "email": "nelsonfilho@ifam.edu.br",
    "tipoSanguineo": "A+",
    "situacaoSaude": "BOM",
    "cidade": {
        "id": 0
    }
}
```