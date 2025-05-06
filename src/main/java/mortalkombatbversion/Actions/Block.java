package mortalkombatbversion.Actions;


import mortalkombatbversion.Components.GameCharacter;

public class Block extends Action {

    /**
     * @return
     */
    @Override
    public String getType() {
        return "Block";
    }

    /**
     * @param human
     * @param enemy
     * @param enemyActionType
     */
    @Override
    public void realisation(GameCharacter human, GameCharacter enemy, String enemyActionType) {
    }
}
