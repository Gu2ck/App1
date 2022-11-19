/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app1;

/**
 *
 * @author gunte
 */
public class DecDotted {
    public DecDotted(String myIp, int netBits)
    {
        ipDec = new int[4];
        netDec = new int[4];
        invertedNetDec = new int[4];
        minIP = new int[4];
        maxIP = new int[4];
        
        String[] parts = myIp.split("\\.");
        if (parts.length != 4)
        {
            ArrayIndexOutOfBoundsException ex = new ArrayIndexOutOfBoundsException("X.X.X.X expected");
            throw ex;
        }
        for (int i=0; i<4; i++)
        {
            ipDec[i] = Integer.parseInt(parts[i]);
        }
        
        char[] netbits = new char[32];
        binaryNetMask = new String();
        for (int i=1; i<= 32; i++ )
        {
            netbits[i-1] = '0';
            if (i <= netBits)
            {
                netbits[i-1] = '1';
            }
            if (i != 1 && ((i-1) % 8 == 0))
            {
                binaryNetMask += '.';
            }
            binaryNetMask += netbits[i-1];
        }
        
        netDec[0] = netDec[1] = netDec[2] = netDec[3] = 0;
        invertedNetDec[0] = invertedNetDec[1] = invertedNetDec[2] = invertedNetDec[3] = 0;
        for(int i=0; i<8; i++)
        {
            if (netbits[i] == '1') {netDec[0] += Math.pow(2, 7-i);}
            if (netbits[8 + i] == '1') {netDec[1] += Math.pow(2, 7-i);}
            if (netbits[16 + i] == '1') {netDec[2] += Math.pow(2, 7-i);}
            if (netbits[24 + i] == '1') {netDec[3] += Math.pow(2, 7-i);}

            if (netbits[i] == '0') {invertedNetDec[0] += Math.pow(2, 7-i);}
            if (netbits[8 + i] == '0') {invertedNetDec[1] += Math.pow(2, 7-i);}
            if (netbits[16 + i] == '0') {invertedNetDec[2] += Math.pow(2, 7-i);}
            if (netbits[24 + i] == '0') {invertedNetDec[3] += Math.pow(2, 7-i);}
        }
        for (int i=0; i<4; i++)
        {
            minIP[i] = ipDec[i] & netDec[i];
            maxIP[i] = ipDec[i] | invertedNetDec[i];
        }
        minIP = add(minIP, 1);
        maxIP = add(maxIP, -1);
    }
    
    public String asString()
    {
        String ret;
        ret = "IP " + ipDec[0] + "." + ipDec[1] + "." + ipDec[2] + "." + ipDec[3] ;
        ret += ", NetBits " + binaryNetMask;
        ret += ", Net Mask " + netDec[0] + "." + netDec[1] + "." + netDec[2] + "." + netDec[3] ;
        ret += ", minIP " + minIP[0] + "." + minIP[1] + "." + minIP[2] + "." + minIP[3] ;
        ret += ", maxIP " + maxIP[0] + "." + maxIP[1] + "." + maxIP[2] + "." + maxIP[3] ;
        
        return ret;
    }
    
    private int[] add(int[] ip, int n)
    {
        int[] ret = ip;
        int tmp = ip[3] + n;
        if (tmp > 255)
        {
            ret[3] = 255;
            ret[2] += tmp % 255;
        }
        else
        {
            ret[3] = tmp;
        }
        return ret;
    }
    
    private int[] ipDec;
    private String binaryNetMask;
    private int[] netDec;
    private int[] invertedNetDec;
    private int[] minIP;
    private int[] maxIP;
}
