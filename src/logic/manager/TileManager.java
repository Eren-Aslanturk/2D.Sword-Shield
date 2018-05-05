package logic.manager;

import gui.UnitCardPanel;
import logic.factory.TileFactory;
import util.Boundary;
import entity.Tile;

import java.util.ArrayList;

import java.awt.*;

/*
* the map is a 10 x 10 grid which consists of 50*50px tiles.
*
* we have two rectangular regions for attacker tiles:
* (0, 100) to (200, 200) and
* (0, 300) to (200, 400)
*
* we have two rectangular regions for defender tiles:
* (300, 100) to (500, 200) and
* (300, 300) to (500, 400)
*
* road tiles are placed right in the middle
* between (0, 225) to (500, 275)
*/

public class TileManager extends UnitCardPanel {
    private TileFactory tileFactory;
    private ArrayList<Tile> attackerTiles;
    private ArrayList<Tile> defenderTiles;
    private ArrayList<Tile> roadTiles;

    // initialize tiles
    public TileManager() {
        tileFactory = new TileFactory();
        attackerTiles = new ArrayList<>();
        defenderTiles = new ArrayList<>();
        roadTiles = new ArrayList<>();

        initDefenderTiles();
        initAttackerTiles();
        initRoadTiles();
    }

    // initialize defender tiles
    private void initDefenderTiles() {
        for (int i = 300; i < 500; i += TileFactory.WIDTH) {
            for (int j = 100; j < 200; j += TileFactory.HEIGHT)
                defenderTiles.add(tileFactory.create(0, i, j));
            for (int j = 300; j < 400; j+= TileFactory.HEIGHT)
                defenderTiles.add(tileFactory.create(0, i, j));
        }
    }

    // initialize attacker tiles
    private void initAttackerTiles(){
        for (int i = 0; i < 200; i += TileFactory.WIDTH) {
            for (int j = 100; j < 200; j += TileFactory.HEIGHT)
                attackerTiles.add(tileFactory.create(0, i, j));
            for (int j = 300; j < 400; j+= TileFactory.HEIGHT)
                attackerTiles.add(tileFactory.create(0, i, j));
        }
    }

    private void initRoadTiles() {
        //initialize the file from the array
        for (int j = 0; j < 500; j+= TileFactory.HEIGHT)
            roadTiles.add(tileFactory.create(1, j, 225));
    }

    public void removeTile(int x, int y) {
        for (int i = 0; i < attackerTiles.size();++i) {
            if (attackerTiles.get(i).getX() == x &&
                    attackerTiles.get(i).getY() == y)
                {
                    attackerTiles.remove(i);
                    return ;
                }
        }

        for (int i = 0; i < defenderTiles.size();++i) {
            if (defenderTiles.get(i).getX() == x &&
                    defenderTiles.get(i).getY() == y)
            {
                defenderTiles.remove(i);
                return ;
            }
        }
        System.out.println("tile is not found");
    }

    //find a tile with a given location
    public Tile findTile(int x, int y){
        //attacker tile
        for (int i = 0; i < attackerTiles.size(); i ++) {
            //match case
            if(attackerTiles.get(i).getX()==x && attackerTiles.get(i).getX()==y)
                return attackerTiles.get(i);
        }
        //not found
        return null;
    }

    public String getTileType(Tile tile) {
        for (int i = 0; i < attackerTiles.size(); ++i)
            if(tile.getX() == attackerTiles.get(i).getX() &&
                    tile.getY() == attackerTiles.get(i).getY())
                return "Attacker";

        for (int i = 0; i < defenderTiles.size(); ++i)
            if(tile.getX() == defenderTiles.get(i).getX() &&
                    tile.getY() == defenderTiles.get(i).getY())
                return "Defender";
        return "None";
    }

    public Tile clickedTile(int x, int y) {
        int normalizedX = x - x % TileFactory.WIDTH;
        int normalizedY = y - y % TileFactory.HEIGHT;

        for (Tile tile : attackerTiles) {
            if (tile.getX() == normalizedX &&
                    tile.getY() == normalizedY )
                return tile;
        }

        for (Tile tile : defenderTiles) {
            if (tile.getX() == normalizedX &&
                    tile.getY() == normalizedY)
                return tile;
        }

        return null;
    }

    public void render(Graphics g) {
        for (Tile tile : attackerTiles)
            g.drawImage(tile.getImage(), tile.getX(), tile.getY(), null);

        for (Tile tile : defenderTiles)
            g.drawImage(tile.getImage(), tile.getX(), tile.getY(), null);

        for (Tile tile: roadTiles)
            g.drawImage(tile.getImage(), tile.getX(), tile.getY(), null);
    }

}
