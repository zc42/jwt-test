# jwt-test

rest controller to test/remember spring boot

---

### **API Information**
- **Title**: OpenAPI definition
- **Version**: v0

---

### **Base Server**
- **URL**: `http://localhost:1235/test-api/v1`
- **Description**: Generated server URL

---

### **Endpoints**

#### **1. POST /file**
- **Description**: Uploads a file.
- **Tags**: `jwt-controller`
- **Operation ID**: `uploadFile`
- **Request Body**:
    - **Content-Type**: `multipart/form-data`
    - **Schema**:
        - **Type**: Object
        - **Required Fields**: `file`
        - **Properties**:
            - **file**:
                - **Type**: `string`
                - **Format**: `binary`
- **Responses**:
    - **200 OK**:
        - **Description**: Request was successful.
        - **Content**:
            - **Schema Type**: Object

---

#### **2. GET /requestMatchers**
- **Description**: Retrieves a list of request matchers.
- **Tags**: `jwt-controller`
- **Operation ID**: `getRequestMatchers`
- **Responses**:
    - **200 OK**:
        - **Description**: Request was successful.
        - **Content**:
            - **Type**: `array`
            - **Items**:
                - **Type**: `string`

---

#### **3. GET /jwt**
- **Description**: Generates a new JWT token.
- **Tags**: `jwt-controller`
- **Operation ID**: `generateTokenV2`
- **Responses**:
    - **200 OK**:
        - **Description**: Request was successful.
        - **Content**:
            - **Type**: `string`

---

#### **4. GET /**
- **Description**: Provides a greetings object and a list of available endpoints.
- **Tags**: `jwt-controller`
- **Operation ID**: `greetings_1`
- **Responses**:
    - **200 OK**:
        - **Description**: Request was successful.
        - **Content**:
            - **Schema Reference**: `#/components/schemas/Greetings`

---

### **Schemas**

#### **Greetings Object**
- **Type**: Object
- **Properties**:
    1. **greetings**:
        - **Type**: `array`
        - **Items**:
            - **Type**: `string`
    2. **availableEndpoints**:
        - **Type**: `array`
        - **Items**:
            - **Type**: `string`

---
