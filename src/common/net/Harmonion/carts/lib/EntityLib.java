package net.Harmonion.carts.lib;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityEggInfo;
import net.minecraft.src.EntityList;
import net.minecraft.src.Item;

public class EntityLib {
	
	private static int startEntityId = 300;
	private static int startItemId = 0;

	public static int getUniqueEntityId() {
		do {
			startEntityId++;
		} while (EntityList.getStringFromID(startEntityId) != null);

		return startEntityId;
	}

	public static int getUniqueItemId() {
		do {
			startItemId++;
			if (startItemId > 32000)
				Logger.getLogger("Minecraft").log(Level.SEVERE,
						"No more Item slots for item id " + startItemId);

		} while (Item.itemsList[256 + startItemId] != null);

		return startItemId;

	}

	/**@SuppressWarnings("unchecked")
	public static void registerEntityEgg(Class<? extends Entity> entity,
			int primaryColor, int secondaryColor) {
		int id = getUniqueEntityId();
		EntityList.IDtoClassMapping.put(id, EntityHarmonionWolf.class);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor,
				secondaryColor));
	}*/
	
}
