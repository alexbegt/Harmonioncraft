package net.Harmonioncraft.client.core.handlers;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import net.minecraft.client.Minecraft;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import net.minecraft.client.Minecraft;
import net.minecraft.src.ThreadDownloadResources;

public class ThreadDownloadResourcesHandler extends Thread
{
    public File resourcesFolder;
    private Minecraft mc;

    public ThreadDownloadResourcesHandler(File var1, Minecraft var2)
    {
        this.mc = var2;
        this.setName("Harmonioncraft Resource download thread");
        this.setDaemon(true);
        this.resourcesFolder = new File(var1, "/mods/Harmonioncraft/resources");

        if (!this.resourcesFolder.exists() && !this.resourcesFolder.mkdirs())
        {
            throw new RuntimeException("The working directory could not be created: " + this.resourcesFolder);
        }
    }

    public void run()
    {
        try
        {
            URL var1 = new URL("https://dl.dropbox.com/u/48633261/HarmonioncraftFiles/jenkins/Files/Harmonioncraft/resources/fileslist.xml");
            DocumentBuilderFactory var2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder var3 = var2.newDocumentBuilder();
            URLConnection var4 = var1.openConnection();
            var4.setConnectTimeout(60000);
            var4.setReadTimeout(60000);
            Document var5 = var3.parse(var4.getInputStream());
            NodeList var6 = var5.getElementsByTagName("File");

            for (int var7 = 0; var7 < 2; ++var7)
            {
                for (int var8 = 0; var8 < var6.getLength(); ++var8)
                {
                    Node var9 = var6.item(var8);

                    if (var9.getNodeType() == 1)
                    {
                        Element var10 = (Element)var9;
                        String var11 = var10.getElementsByTagName("Path").item(0).getChildNodes().item(0).getNodeValue();
                        long var12 = Long.parseLong(var10.getElementsByTagName("Size").item(0).getChildNodes().item(0).getNodeValue());

                        if (var12 > 0L)
                        {
                            this.downloadAndInstallResource(var1, var11, var12, var7);

                            if (this.getIsClosing())
                            {
                                return;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception var14)
        {
            this.loadResource(this.resourcesFolder, "");
            var14.printStackTrace();
        }
    }

    public void reloadResources()
    {
        this.loadResource(this.resourcesFolder, "");
    }

    private void loadResource(File var1, String var2)
    {
        File[] var3 = var1.listFiles();
        File[] var4 = var3;
        int var5 = var3.length;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            File var7 = var4[var6];

            if (var7.isDirectory())
            {
                this.loadResource(var7, var2 + var7.getName() + "/");
            }
            else
            {
                try
                {
                    this.mc.installResource(var2 + var7.getName(), var7);
                }
                catch (Exception var9)
                {
                    System.out.println("Failed to add " + var2 + var7.getName());
                }
            }
        }
    }

    private void downloadAndInstallResource(URL var1, String var2, long var3, int var5)
    {
        try
        {
            int var6 = var2.indexOf("/");
            String var7 = var2.substring(0, var6);

            if (!var7.equals("sound") && !var7.equals("newsound"))
            {
                if (var5 != 1)
                {
                    return;
                }
            }
            else if (var5 != 0)
            {
                return;
            }

            File var8 = new File(this.resourcesFolder, var2);

            if (!var8.exists() || var8.length() != var3)
            {
                var8.getParentFile().mkdirs();
                String var9 = var2.replaceAll(" ", "%20");
                this.downloadResource(new URL(var1, var9), var8, var3);

                if (this.getIsClosing())
                {
                    return;
                }
            }

            var2 = var2.replaceFirst("Harmonioncraft", "HMC");
            this.mc.installResource(var2, var8);
        }
        catch (Exception var10)
        {
            var10.printStackTrace();
        }
    }

    private void downloadResource(URL var1, File var2, long var3) throws IOException
    {
        byte[] var5 = new byte[4096];
        URLConnection var6 = var1.openConnection();
        var6.setConnectTimeout(60000);
        var6.setReadTimeout(60000);
        DataInputStream var7 = new DataInputStream(var6.getInputStream());
        DataOutputStream var8 = new DataOutputStream(new FileOutputStream(var2));
        boolean var9 = false;
        int var10;

        while ((var10 = var7.read(var5)) >= 0)
        {
            var8.write(var5, 0, var10);

            if (this.getIsClosing())
            {
                return;
            }
        }

        var7.close();
        var8.close();
    }

    public boolean getIsClosing()
    {
        boolean var1 = false;
        ThreadDownloadResources var2 = null;

        try
        {
            var2 = (ThreadDownloadResources)ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, this.mc, new String[] {"V", "downloadResourcesThread"});

            if (var2 != null)
            {
                var1 = ((Boolean)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadResources.class, var2, new String[] {"c", "closing"})).booleanValue();
            }
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        return var1;
    }
}
