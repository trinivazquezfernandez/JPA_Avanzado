package main;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class SimplePersistenceInfo implements PersistenceUnitInfo {
    private final String name;

    public SimplePersistenceInfo(String name) {
        this.name = name;
    }

    public String getPersistenceUnitName() {
        return name;
    }

    public String getPersistenceProviderClassName() {
        return null;
    }

    public PersistenceUnitTransactionType getTransactionType() {
        return null;
    }

    public DataSource getJtaDataSource() {
        return null;
    }

    public DataSource getNonJtaDataSource() {
        return null;
    }

    public List<String> getMappingFileNames() {
        return null;
    }

    public List<URL> getJarFileUrls() {
        try{
            return Collections.list(this.getClass()
            .getClassLoader()
            .getResources(""));

        } catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    public List<String> getManagedClassNames() {
        return null;
    }

    public boolean excludeUnlistedClasses() {
        return false;
    }

    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    public ValidationMode getValidationMode() {
        return null;
    }

    public Properties getProperties() {
        return null;
    }

    public String getPersistenceXMLSchemaVersion() {
        return null;
    }

    public ClassLoader getClassLoader() {
        return null;
    }

    public void addTransformer(ClassTransformer transformer) {

    }

    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
