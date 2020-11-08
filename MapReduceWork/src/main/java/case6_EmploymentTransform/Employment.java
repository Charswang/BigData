package case6_EmploymentTransform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employment implements Writable {
    private String id;
    private String company_name;
    private String eduLevel_name;
    private String emplType;
    private String jobName;
    private String salary;
    private String createDate;
    private String endDate;
    private String city_code;
    private String companySize;
    private String welfare;
    private String responsibility;
    private String place;
    private String workingExp;

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(id);
        dataOutput.writeUTF(company_name);
        dataOutput.writeUTF(eduLevel_name);
        dataOutput.writeUTF(emplType);
        dataOutput.writeUTF(jobName);
        dataOutput.writeUTF(salary);
        dataOutput.writeUTF(createDate);
        dataOutput.writeUTF(endDate);
        dataOutput.writeUTF(city_code);
        dataOutput.writeUTF(companySize);
        dataOutput.writeUTF(welfare);
        dataOutput.writeUTF(responsibility);
        dataOutput.writeUTF(place);
        dataOutput.writeUTF(workingExp);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        id = dataInput.readUTF();
        company_name = dataInput.readUTF();
        eduLevel_name = dataInput.readUTF();
        emplType = dataInput.readUTF();
        jobName = dataInput.readUTF();
        salary = dataInput.readUTF();
        createDate = dataInput.readUTF();
        endDate = dataInput.readUTF();
        city_code = dataInput.readUTF();
        companySize = dataInput.readUTF();
        welfare = dataInput.readUTF();
        responsibility = dataInput.readUTF();
        place = dataInput.readUTF();
        workingExp = dataInput.readUTF();
    }

    @Override
    public String toString() {
        return id + "\t" + company_name + "\t"  + eduLevel_name + "\t" + emplType + "\t" + jobName + "\t" + salary + "\t" + createDate + "\t"+
                 endDate + "\t" + city_code + "\t" + companySize + "\t" + welfare + "\t" + responsibility + "\t" + place + "\t" + workingExp ;
    }
}
