package base;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility extends AbstractModule {
    @Override
    protected void configure(){
        try {
            Properties prop= new Properties();
            prop.load(new FileInputStream(FilePath.COMMON_DATA));
            Names.bindProperties(binder(),prop);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("File not found");
        }
    }
}
