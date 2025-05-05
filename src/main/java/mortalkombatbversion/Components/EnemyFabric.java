package mortalkombatbversion.Components;


import static mortalkombatbversion.Components.CharacterName.*;


public class EnemyFabric {
    public GameCharacter create(CharacterName characterName) {
        return switch (characterName){
            default -> null;
            case BARAKA -> new GameCharacter(1, 100, 12, BARAKA, "/baraka.jpeg");
            case LIU_KANG -> new GameCharacter(1, 70, 20, LIU_KANG, "/liukang.jpg");
            case SONYA_BLADE -> new GameCharacter(1, 80, 16, SONYA_BLADE, "/sonyablade.png");
            case SUB_ZERO -> new GameCharacter(1, 60, 16, SUB_ZERO, "/subzero.png");
            case SHAO_KAHN -> new GameCharacter(3, 100, 30, SHAO_KAHN, "/kao.jpeg");
        };
    }
}