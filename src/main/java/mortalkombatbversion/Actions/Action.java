package mortalkombatbversion.Actions;


import mortalkombatbversion.Components.GameCharacter;

public abstract class Action {
    /**
     *
     * @return
     */
    public abstract String getType();

    /**
     *
     * @param gameCharacter
     * @param enemy
     * @param enemyActionType
     */
    public abstract void realisation(GameCharacter gameCharacter, GameCharacter enemy, String enemyActionType);
}
