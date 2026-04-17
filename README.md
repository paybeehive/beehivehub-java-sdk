# Beehive Hub Java SDK

Official SDK for integrating with the Beehive Hub API. Accept payments simply and quickly.

[![Maven Central](https://img.shields.io/maven-central/v/br.com.paybeehive/beehivehub-java-sdk)](https://central.sonatype.com/artifact/br.com.paybeehive/beehivehub-java-sdk)
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)

## Table of Contents

- [Installation](#installation)
- [Quick Start](#quick-start)
- [Authentication](#authentication)
- [Resources](#resources)
  - [Transactions](#transactions)
  - [Customers](#customers)
  - [Transfers](#transfers)
  - [Balance](#balance)
  - [Recipients](#recipients)
  - [Bank Accounts](#bank-accounts)
  - [Company](#company)
  - [Payment Links](#payment-links)
- [Error Handling](#error-handling)
- [Values in Cents](#values-in-cents)
- [Security Best Practices](#security-best-practices)
- [Testing](#testing)
- [Support](#support)
- [License](#license)

## Installation

Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>br.com.paybeehive</groupId>
    <artifactId>beehivehub-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

Or with Gradle:

```gradle
implementation 'br.com.paybeehive:beehivehub-java-sdk:1.0.0'
```

## Quick Start

```java
import br.com.paybeehive.sdk.BeehiveHubClient;
import br.com.paybeehive.sdk.models.Transaction;
import br.com.paybeehive.sdk.requests.CreateTransactionRequest;

BeehiveHubClient beehive = new BeehiveHubClient("your_secret_key");

CreateTransactionRequest request = new CreateTransactionRequest();
request.setAmount(10000L);        // BRL 100.00 in cents
request.setPaymentMethod("pix");
request.setPostbackUrl("https://example.com/webhook");

Transaction transaction = beehive.transactions.create(request);
System.out.println("Transaction created: " + transaction.getId());
```

## Authentication

The SDK uses **Basic Authentication**. Provide your **SECRET_KEY** when initializing.

### Getting your credentials

1. Access the [Beehive Hub dashboard](https://app.conta.paybeehive.com.br)
2. Navigate to **Settings → API Credentials**
3. Copy your **SECRET_KEY**

```java
BeehiveHubClient beehive = new BeehiveHubClient("your_secret_key_here");
```

### Sandbox environment

```java
BeehiveHubClient beehive = new BeehiveHubClient("your_secret_key", "sandbox");
```

**Important:** Never expose your secret key in source code or public repositories. Use environment variables:

```java
BeehiveHubClient beehive = new BeehiveHubClient(System.getenv("BEEHIVE_SECRET_KEY"));
```

## Resources

### Transactions

#### Create a transaction

```java
CreateTransactionRequest request = new CreateTransactionRequest();
request.setAmount(10000L);        // BRL 100.00 in cents
request.setPaymentMethod("pix");
request.setPostbackUrl("https://example.com/webhook");

Transaction transaction = beehive.transactions.create(request);
```

#### List transactions

```java
ListTransactionsParams params = new ListTransactionsParams();
params.setLimit(100);
params.setOffset(0);
params.setStartDate("2026-01-01T00:00:00");

List<Transaction> transactions = beehive.transactions.list(params);
```

#### Get a transaction

```java
Transaction transaction = beehive.transactions.get(123456L);
```

#### Refund a transaction

```java
// Full refund
beehive.transactions.refund(123456L, null);

// Partial refund
beehive.transactions.refund(123456L, 5000L);
```

#### Update delivery status

```java
UpdateDeliveryStatusRequest request = new UpdateDeliveryStatusRequest();
request.setStatus("in_transit");
request.setTrackingCode("BR123456789");

beehive.transactions.updateDelivery(123456L, request);
```

### Customers

#### Create a customer

```java
import br.com.paybeehive.sdk.models.Address;
import br.com.paybeehive.sdk.models.Document;

Address address = new Address();
address.setStreet("Av. Paulista");
address.setStreetNumber("1578");
address.setComplement("Apto 101");
address.setNeighborhood("Bela Vista");
address.setZipCode("01310100");
address.setCity("Sao Paulo");
address.setState("SP");
address.setCountry("br");

Document document = new Document();
document.setType("cpf");
document.setNumber("98765432100");

CreateCustomerRequest request = new CreateCustomerRequest();
request.setName("Maria Santos");
request.setEmail("maria@example.com");
request.setPhone("11988888888");
request.setDocument(document);
request.setAddress(address);

Customer customer = beehive.customers.create(request);
```

#### List customers

The `email` parameter is required.

```java
List<Customer> customers = beehive.customers.list("cliente@example.com");
```

#### Get a customer

```java
Customer customer = beehive.customers.get(123456L);
```

### Transfers

#### Create a transfer

```java
CreateTransferRequest request = new CreateTransferRequest();
request.setAmount(50000L);
request.setRecipientId(916L);

Transfer transfer = beehive.transfers.create(request);
```

#### Get a transfer

```java
Transfer transfer = beehive.transfers.get(123456L);
```

### Balance

```java
Balance balance = beehive.balance.get();
System.out.println("Available: BRL " + balance.getAmount() / 100.0);
System.out.println("Recipient ID: " + balance.getRecipientId());
```

### Recipients

#### Create a recipient

```java
CreateRecipientRequest request = new CreateRecipientRequest();
request.setLegalName("Recebedor Teste Ltda");

Recipient recipient = beehive.recipients.create(request);
```

#### List recipients

```java
List<Recipient> recipients = beehive.recipients.list();
```

#### Get a recipient

```java
Recipient recipient = beehive.recipients.get(916L);
```

#### Update a recipient

```java
UpdateRecipientRequest request = new UpdateRecipientRequest();
request.setLegalName("Beehive Sandbox");

Recipient updated = beehive.recipients.update(916L, request);
```

### Bank Accounts

#### Add a bank account

```java
CreateBankAccountRequest request = new CreateBankAccountRequest();
request.setBankCode("341");
request.setAgencyNumber("9876");
request.setAccountNumber("54321");
request.setAccountDigit("0");
request.setType("conta_poupanca");
request.setLegalName("Empresa Teste Ltda");
request.setDocumentNumber("60572883000136");
request.setDocumentType("cnpj");

BankAccount bankAccount = beehive.bankAccounts.create(916L, request);
```

#### List bank accounts

```java
List<BankAccount> accounts = beehive.bankAccounts.list(916L);
```

### Company

#### Get company data

```java
Company company = beehive.company.get();
```

#### Update company data

```java
UpdateCompanyRequest request = new UpdateCompanyRequest();
request.setInvoiceDescriptor("Beehive Hub");

UpdateCompanyRequest.Details details = new UpdateCompanyRequest.Details();
details.setAverageRevenue(10000L);
details.setAverageTicket(100L);
details.setPhysicalProducts(true);
details.setProductsDescription("Produtos físicos");
details.setSiteUrl("https://www.meusite.com.br");
details.setPhone("11999999999");
details.setEmail("contato@meusite.com.br");
request.setDetails(details);

Company updated = beehive.company.update(request);
```

### Payment Links

The SDK automatically appends `url` to responses (create, get, list, update) when an `alias` is present:

- **Production:** `https://link.conta.paybeehive.com.br/{alias}`
- **Sandbox:** `https://link.sandbox.hopysplit.com.br/{alias}`

If `alias` is not provided, the SDK auto-generates a 10-character alphanumeric code.

#### Create a payment link

```java
CreatePaymentLinkRequest request = new CreatePaymentLinkRequest();
request.setTitle("Meu Link de Pagamento");
request.setAmount(1000L);

PaymentLink paymentLink = beehive.paymentLinks.create(request);
// paymentLink.getUrl() is automatically populated
```

#### List payment links

```java
List<PaymentLink> paymentLinks = beehive.paymentLinks.list();
```

#### Get a payment link

```java
PaymentLink paymentLink = beehive.paymentLinks.get(247L);
```

#### Update a payment link

```java
UpdatePaymentLinkRequest request = new UpdatePaymentLinkRequest();
request.setTitle("Link Atualizado");
request.setAmount(2000L);

PaymentLink updated = beehive.paymentLinks.update(247L, request);
```

#### Delete a payment link

```java
beehive.paymentLinks.delete(247L);
```

## Error Handling

The SDK throws specific exception classes for different scenarios:

- `BeehiveHubAPIError` - General API errors (4xx, 5xx)
- `BeehiveHubAuthenticationError` - Authentication failures (401)
- `BeehiveHubValidationError` - Request validation errors (400)
- `BeehiveHubNotFoundError` - Resource not found (404)
- `BeehiveHubRateLimitError` - Rate limit exceeded (429)
- `BeehiveHubNetworkError` - Network/connection errors

```java
import br.com.paybeehive.sdk.BeehiveHubClient;
import br.com.paybeehive.sdk.exceptions.*;

BeehiveHubClient beehive = new BeehiveHubClient(System.getenv("BEEHIVE_SECRET_KEY"));

try {
    Transaction transaction = beehive.transactions.create(request);
    System.out.println("Transaction created: " + transaction.getId());
} catch (BeehiveHubAuthenticationError e) {
    System.err.println("Invalid API key: " + e.getMessage());
} catch (BeehiveHubValidationError e) {
    System.err.println("Validation error: " + e.getMessage());
} catch (BeehiveHubNotFoundError e) {
    System.err.println("Not found: " + e.getMessage());
} catch (BeehiveHubRateLimitError e) {
    System.err.println("Rate limit exceeded: " + e.getMessage());
} catch (BeehiveHubAPIError e) {
    System.err.println("API error: " + e.getMessage());
} catch (BeehiveHubNetworkError e) {
    System.err.println("Network error: " + e.getMessage());
}
```

## Values in Cents

All monetary values in the API are expressed in **cents**.

```java
// BRL 100.00 = 10000 cents
request.setAmount(10000L);

// BRL 1.50 = 150 cents
request.setAmount(150L);

// Convert reais to cents
double reais = 100.0;
long cents = Math.round(reais * 100); // 10000
```

## Security Best Practices

1. **Never expose your SECRET_KEY** - Use environment variables
2. **Validate user data** - Always validate and sanitize before sending to API
3. **Use HTTPS** - Always use secure connections
4. **Implement webhooks** - Receive status change notifications

```java
// Set environment variable: BEEHIVE_SECRET_KEY=your_secret_key_here

BeehiveHubClient beehive = new BeehiveHubClient(System.getenv("BEEHIVE_SECRET_KEY"));
```

## Additional Documentation

- [Official API Documentation](https://docs.beehivehub.io/api-reference/transa%C3%A7%C3%B5es/listar-transa%C3%A7%C3%B5es)
- [Integration Guide](https://docs.beehivehub.io/introdu%C3%A7%C3%A3o)
- [Card Tokenization](https://docs.beehivehub.io/quickstart)

## Testing

```bash
./mvnw test
```

```bash
./mvnw verify
```

## Support

For suggestions, bug reports, or questions:

- **Email:** [contato@paybeehive.com.br](mailto:contato@paybeehive.com.br)
- **Documentation:** [https://docs.beehivehub.io](https://docs.beehivehub.io/)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
