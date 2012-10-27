package net.Harmonioncraft;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.ModLoader;

public class DartSettingsHandler
{
    File duppedFile;
    File fileActual;

    public DartSettingsHandler() throws IOException
    {
        this.duppedFile = new File(ModLoader.getMinecraftInstance().mcDataDir, "spOption.txt");
        this.fileActual = new File(ModLoader.getMinecraftInstance().mcDataDir, "options.txt");

        if (this.hasBeenChanged())
        {
            this.resaveOptions();
        }
    }

    public void dupeOptions(int var1) throws IOException
    {
        try
        {
            BufferedReader var2 = new BufferedReader(new FileReader(this.fileActual));
            PrintWriter var3 = new PrintWriter(new FileWriter(this.duppedFile));
            var3.println(var1 + ":i");
            String var4 = "";

            while ((var4 = var2.readLine()) != null)
            {
                var3.println(var4);
            }

            var3.close();
            var2.close();
        }
        catch (FileNotFoundException var5)
        {
            var5.printStackTrace();
        }
    }

    public void resaveOptions() throws IOException
    {
        BufferedReader var1 = new BufferedReader(new FileReader(this.duppedFile));
        String var2 = "";

        while ((var2 = var1.readLine()) != null)
        {
            System.out.println(var2);
            String[] var3 = var2.split(":");

            if (var3[0].equals("mouseSensitivity"))
            {
                ModLoader.getMinecraftInstance().gameSettings.mouseSensitivity = this.parseFloat(var3[1]);
            }

            if (var3[0].equals("fov"))
            {
                ModLoader.getMinecraftInstance().gameSettings.fovSetting = this.parseFloat(var3[1]);
            }

            if (var3[0].equals("key_key.attack"))
            {
                ModLoader.getMinecraftInstance().gameSettings.keyBindAttack = new KeyBinding("key.attack", Integer.parseInt(var3[1]));
            }

            if (var3[0].equals("key_key.use"))
            {
                ModLoader.getMinecraftInstance().gameSettings.keyBindUseItem = new KeyBinding("key.use", Integer.parseInt(var3[1]));
            }

            if (var3[0].equals("key_key.back"))
            {
                ModLoader.getMinecraftInstance().gameSettings.keyBindBack = new KeyBinding("key.back", Integer.parseInt(var3[1]));
            }

            if (var3[0].equals("key_key.forward"))
            {
                ModLoader.getMinecraftInstance().gameSettings.keyBindForward = new KeyBinding("key.forward", Integer.parseInt(var3[1]));
            }

            if (var3[0].equals("key_key.left"))
            {
                ModLoader.getMinecraftInstance().gameSettings.keyBindLeft = new KeyBinding("key.left", Integer.parseInt(var3[1]));
            }

            if (var3[0].equals("key_key.right"))
            {
                ModLoader.getMinecraftInstance().gameSettings.keyBindRight = new KeyBinding("key.right", Integer.parseInt(var3[1]));
            }

            if (var3[0].equals("key_key.jump"))
            {
                ModLoader.getMinecraftInstance().gameSettings.keyBindJump = new KeyBinding("key.jump", Integer.parseInt(var3[1]));
            }
        }

        var1.close();
        this.dupeOptions(0);
    }

    public boolean hasBeenChanged() throws IOException
    {
        if (!this.duppedFile.exists())
        {
            this.dupeOptions(0);
            return false;
        }
        else
        {
            BufferedReader var1 = new BufferedReader(new FileReader(this.duppedFile));

            if (var1.readLine().charAt(0) == 49)
            {
                var1.close();
                return true;
            }
            else
            {
                var1.close();
                return false;
            }
        }
    }

    private float parseFloat(String var1)
    {
        return var1.equals("true") ? 1.0F : (var1.equals("false") ? 0.0F : Float.parseFloat(var1));
    }
}
