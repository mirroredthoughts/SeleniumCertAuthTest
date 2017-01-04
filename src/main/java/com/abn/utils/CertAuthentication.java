package com.abn.utils;


import ch.racic.caps.CAPServer;
import ch.racic.caps.CapsConfiguration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Proxy;

import java.io.File;
import java.net.InetAddress;

public class CertAuthentication {

    public CAPServer capServer;
    public CapsConfiguration capsConfiguration;
    public static Proxy proxy;

    private static String authPass;
    private static String keyStoreType;
    private static String certPath;


    public void getCertificate(String certLocation) throws Exception {
        certPath = certLocation;
        File certificateFile = FileUtils.toFile(ClassLoader.getSystemResource(certPath));
        if(certificateFile == null || !certificateFile.exists()) {
            throw new Exception("Certificate file is missing: " +certLocation);
        }
        authPass = "abcd1234";
        keyStoreType = "PKCS12";
    }

    public void startProxyServer() throws Exception {
        capsConfiguration = new CapsConfiguration();
        capsConfiguration.setTargetKeyStorePath(certPath).setTargetKeyStorePassword(authPass).setTargetKeyStoreType(keyStoreType);
        capServer = CAPServer.bringItUpRunning(capsConfiguration);
        proxy = new Proxy().setSslProxy(InetAddress.getLocalHost().getHostAddress() + ":" + capServer.getProxyListenerPort());
    }

    public static Proxy getProxy() {
        return proxy;
    }

    public void callCertificate(String certificate) throws Exception {
        getCertificate(certificate);
        startProxyServer();
    }
}
