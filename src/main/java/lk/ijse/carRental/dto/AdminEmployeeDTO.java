package lk.ijse.carRental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AdminEmployeeDTO {
    private String serviceId;
    private String nic;
    private String name;
    private String contactnumber;
    private String email;
    private String username;
    private String password;


}
