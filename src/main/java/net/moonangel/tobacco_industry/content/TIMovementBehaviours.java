package net.moonangel.tobacco_industry.content;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllMovementBehaviours;
import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;

public class TIMovementBehaviours {
    public static void register() {
        MovementBehaviour original = AllMovementBehaviours.getBehaviour(AllBlocks.MECHANICAL_HARVESTER.getDefaultState());
        AllMovementBehaviours.registerBehaviour(AllBlocks.MECHANICAL_HARVESTER.get(), new TobaccoCropHarvesterMovementBehaviour(original));
    }
}
