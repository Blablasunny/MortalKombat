package mortalkombatbversion.Game;

import mortalkombatbversion.Components.EnemyFabric;
import mortalkombatbversion.Components.Player;
import mortalkombatbversion.Components.Items;
import mortalkombatbversion.Components.GameCharacter;
import mortalkombatbversion.Components.GameResults;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import static mortalkombatbversion.Components.CharacterName.*;

public class Helper {

    public Logic action = new Logic();
    public Fight fight = new Fight();
    private final ArrayList<GameResults> gameResults = new ArrayList<>();
    private final GameCharacter[] enemies = new GameCharacter[5];

    EnemyFabric fabric = new EnemyFabric();

    public void setEnemies() {
        enemies[0] = fabric.create(BARAKA);
        enemies[1] = fabric.create(SUB_ZERO);
        enemies[2] = fabric.create(LIU_KANG);
        enemies[3] = fabric.create(SONYA_BLADE);
        enemies[4] = fabric.create(SHAO_KAHN);
    }

    public GameCharacter[] getEnemies() {
        return this.enemies;
    }

    /**
     * @param mediator
     * @param items
     * @return
     */
    public Player newHuman(Mediator mediator, Items[] items) {
        Player player = new Player(0, 80, 16, You);
        mediator.setHealthBar(player);
        mediator.setPlayerMaxHealthBar(player);
        player.setItems(items);
        return player;
    }

    /**
     * @param player
     * @param text
     * @param table
     * @throws IOException
     */
    public void endGameTop(Player player, JTextField text, JTable table) throws IOException {
        gameResults.add(new GameResults(text.getText(), player.getPoints()));
        gameResults.sort(Comparator.comparing(GameResults::getPoints).reversed());
        writeToTable(table);
        writeToExcel();
    }

    /**
     * @throws IOException
     */
    public void writeToExcel() throws IOException {
        try (XSSFWorkbook book = new XSSFWorkbook()) {
            XSSFSheet sheet = book.createSheet("Результаты ТОП 10");
            XSSFRow r = sheet.createRow(0);
            r.createCell(0).setCellValue("№");
            r.createCell(1).setCellValue("Имя");
            r.createCell(2).setCellValue("Количество баллов");
            for (int i = 0; i < gameResults.size(); i++) {
                if (i < 10) {
                    XSSFRow r2 = sheet.createRow(i + 1);
                    r2.createCell(0).setCellValue(i + 1);
                    r2.createCell(1).setCellValue(gameResults.get(i).getName());
                    r2.createCell(2).setCellValue(gameResults.get(i).getPoints());
                }
            }

            File f = new File("Results.xlsx");
            book.write(new FileOutputStream(f));
        }
    }

    public ArrayList<GameResults> getResults() {
        return this.gameResults;
    }

    /**
     * @throws IOException
     */
    public void readFromExcel() throws IOException {
        XSSFWorkbook book = new XSSFWorkbook(new FileInputStream("Results.xlsx"));
        XSSFSheet sh = book.getSheetAt(0);
        for (int i = 1; i <= sh.getLastRowNum(); i++) {
            gameResults.add(new GameResults(sh.getRow(i).getCell(1).getStringCellValue(), (int) sh.getRow(i).getCell(2).getNumericCellValue()));
        }
    }

    /**
     * @param table
     */
    public void writeToTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < gameResults.size(); i++) {
            if (i < 10) {
                model.setValueAt(gameResults.get(i).getName(), i, 0);
                model.setValueAt(gameResults.get(i).getPoints(), i, 1);
            }
        }
    }
}
