package unittests.renderer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import primitives.Color;
import renderer.ImageWriter;

/**
 * @author Jessica and Shlomit
 *
 */
class ImageWriterTest {

    /**
     * Test method for {@link renderer.ImageWriter#writeToImage()}.
     */
    @Test
    void testWriteToImage() {
        int nY = 500;
        int nX = 800;
        int rows = 10;
        int columns = 16;
        Color grid = new Color(37, 46, 235); //Blue
        Color square = new Color(235, 163, 190); //Baby pink
        ImageWriter images = new ImageWriter("gridImage", nX, nY);

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (((i % (nY/rows)) == 0) || ((j % (nX/columns)) == 0)) {
                    images.writePixel(j, i, grid);
                } else {
                    images.writePixel(j,  i, square);
                }
            }
        }
        images.writeToImage();

    }

    /**
     * Test method for {@link renderer.ImageWriter#writePixel(int, int, primitives.Color)}.
     */
    @Test
    void testWritePixel() {
        //fail("Not yet implemented");
    }

}