package org.example.controller;

import org.example.dto.AddressDto;
import org.example.dto.EmployeeDto;
import org.example.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testCreateEmployee_ValidData_ReturnsCreatedEmployee() throws Exception {
        // Create a valid EmployeeDto object
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("John");
        employeeDto.setLastName("Doe");
        employeeDto.setEmail("john.doe@example.com");

        // Mock the behavior of the employeeService
        when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(employeeDto);

        // Perform POST request with JSON content
        mockMvc.perform(post("/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"john.doe@example.com\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testCreateEmployeeAddress_ValidData_ReturnsCreatedAddress() throws Exception {
        // Create a valid AddressDto object
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("City");
        addressDto.setStreet("Street");
        addressDto.setState("State");
        addressDto.setZipCode("ZipCode");

        // Mock the behavior of the employeeService
        when(employeeService.createEmployeeAddress(anyLong(), any(AddressDto.class))).thenReturn(addressDto);

        // Perform POST request with JSON content
        mockMvc.perform(post("/v1/employee/1/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"city\": \"City\", \"street\": \"Street\", \"state\": \"State\", \"zipCode\": \"ZipCode\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.city").value("City"))
                .andExpect(jsonPath("$.street").value("Street"))
                .andExpect(jsonPath("$.state").value("State"))
                .andExpect(jsonPath("$.zipCode").value("ZipCode"));
    }

    @Test
    public void testUpdateEmployee_ValidData_ReturnsUpdatedEmployee() throws Exception {
        // Create a valid EmployeeDto object
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("John");
        employeeDto.setLastName("Doe");
        employeeDto.setEmail("john.doe@example.com");

        // Mock the behavior of the employeeService
        when(employeeService.updateEmployee(anyLong(), any(EmployeeDto.class))).thenReturn(employeeDto);

        // Perform PUT request with JSON content
        mockMvc.perform(put("/v1/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"john.doe@example.com\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testGetEmployeeById_ExistingId_ReturnsEmployee() throws Exception {
        // Create a valid EmployeeDto object
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setFirstName("John");
        employeeDto.setLastName("Doe");
        employeeDto.setEmail("john.doe@example.com");

        // Mock the behavior of the employeeService
        when(employeeService.getEmployeeById(1L)).thenReturn(employeeDto);

        // Perform GET request
        mockMvc.perform(get("/v1/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testGetAllEmployees_NoParams_ReturnsListOfEmployees() throws Exception {
        // Create a list of EmployeeDto objects
        List<EmployeeDto> employeeList = new ArrayList<>();
        EmployeeDto employee1 = new EmployeeDto();
        employee1.setId(1L);
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setEmail("john.doe@example.com");
        EmployeeDto employee2 = new EmployeeDto();
        employee2.setId(2L);
        employee2.setFirstName("Jane");
        employee2.setLastName("Doe");
        employee2.setEmail("jane.doe@example.com");
        employeeList.add(employee1);
        employeeList.add(employee2);

        // Mock the behavior of the employeeService
        when(employeeService.getAllEmployees()).thenReturn(employeeList);

        // Perform GET request
        mockMvc.perform(get("/v1/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Doe"))
                .andExpect(jsonPath("$[1].email").value("jane.doe@example.com"));
    }

    @Test
    public void testGetAllAddressesOfEmployee_ExistingId_ReturnsListOfAddresses() throws Exception {
        // Create a list of AddressDto objects
        List<AddressDto> addressList = new ArrayList<>();
        AddressDto address1 = new AddressDto();
        address1.setId(1L);
        address1.setCity("City1");
        address1.setStreet("Street1");
        AddressDto address2 = new AddressDto();
        address2.setId(2L);
        address2.setCity("City2");
        address2.setStreet("Street2");
        addressList.add(address1);
        addressList.add(address2);

        // Mock the behavior of the employeeService
        when(employeeService.getAllAddressesOfEmployee(1L)).thenReturn(addressList);

        // Perform GET request
        mockMvc.perform(get("/v1/employee/1/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].city").value("City1"))
                .andExpect(jsonPath("$[0].street").value("Street1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].city").value("City2"))
                .andExpect(jsonPath("$[1].street").value("Street2"));
    }

    @Test
    public void testDeleteEmployee_ExistingId_ReturnsOkStatus() throws Exception {
        // Perform DELETE request
        mockMvc.perform(delete("/v1/employee/1"))
                .andExpect(status().isOk());

        // Verify that the employeeService.deleteEmployee(id) is called
        verify(employeeService, times(1)).deleteEmployee(1L);
    }
}
