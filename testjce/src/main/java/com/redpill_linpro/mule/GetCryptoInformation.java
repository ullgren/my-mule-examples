package com.redpill_linpro.mule;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;

public class GetCryptoInformation {
    
    public String getCryptoInformation() throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("AES max allowed key length is : %d \n" 
                + "JCE Unlimited Strength Jurisdiction Policy likely%s installed.\n",
                Cipher.getMaxAllowedKeyLength("AES"),
                (Cipher.getMaxAllowedKeyLength("AES")>128?"":" not")
                ));
        sb.append("\nInstalled providers and there properties:\n");
        final Provider[] providers = Security.getProviders();
        
        for (final Provider p : providers)
        {
            sb.append(String.format("%s %s\n", p.getName(), p.getVersion()));
            for (final Object o : p.keySet())
            {
                sb.append(String.format("\t%s : %s\n", o, p.getProperty((String)o)));
            }
        }
        return sb.toString();
    }

}
