import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Level 
{
	public int width; 
	public int height; 
	
	public Tile[][] tiles; 
	
	public Level(String path)
	{
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			this.width = map.getWidth(); 
			this.height = map.getHeight(); 
			int[] pixels = new int[width*height]; 
			tiles = new Tile[width][height]; 
			map.getRGB(0, 0, width, height, pixels, 0, width);
			
			for (int xx = 0; xx < width; xx++) //this for-loop method colors the walking path dark blue
			{
				for (int yy = 0; yy < height; yy++)
				{
					int val = pixels[xx + (yy*width)]; 
					
					if (val == 0xFF000000)
					{
						//tile
						tiles[xx][yy] = new Tile(xx*32, yy*32); 
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	public void render(Graphics g)
	{
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				if (tiles[x][y] != null) tiles[x][y].render(g);
			}
		}
	}
}
