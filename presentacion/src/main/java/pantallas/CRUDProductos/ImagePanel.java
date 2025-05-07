package pantallas.CRUDProductos;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.Beans;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImagePanel extends JPanel {

    private transient BufferedImage currentImage;
    private File currentImageFile;
    private static volatile BufferedImage runtimeDefaultImage = null;
    private static final Object defaultImageLock = new Object();
    private static final int FIXED_IMAGE_SIZE = 100;

    public ImagePanel() {
        this.currentImageFile = null;
        Dimension fixedDim = new Dimension(FIXED_IMAGE_SIZE, FIXED_IMAGE_SIZE);
        setPreferredSize(fixedDim);
        setMinimumSize(fixedDim);
        setMaximumSize(fixedDim);

        if (Beans.isDesignTime()) {
            this.currentImage = createDesignTimePlaceholder();
            setBorder(BorderFactory.createEtchedBorder());
            setToolTipText("ImagePanel (100x100)");
        } else {
            this.currentImage = getRuntimeDefaultImage();
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (contains(e.getPoint())) {
                        JFileChooser fileChooser = new JFileChooser();
                        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                "Images (jpg, png, gif, bmp)", "jpg", "jpeg", "png", "gif", "bmp");
                        fileChooser.setFileFilter(filter);
                        Window parentWindow = SwingUtilities.getWindowAncestor(ImagePanel.this);
                        if (fileChooser.showOpenDialog(parentWindow) == JFileChooser.APPROVE_OPTION) {
                            loadImage(fileChooser.getSelectedFile());
                        }
                    }
                }
            });
        }
    }

    private static BufferedImage createDesignTimePlaceholder() {
        BufferedImage placeholder = new BufferedImage(FIXED_IMAGE_SIZE, FIXED_IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = placeholder.createGraphics();
        try {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, FIXED_IMAGE_SIZE, FIXED_IMAGE_SIZE);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(0, 0, FIXED_IMAGE_SIZE - 1, FIXED_IMAGE_SIZE - 1);
            String text = "Imagen";
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            g.drawString(text, (FIXED_IMAGE_SIZE - textWidth) / 2, (FIXED_IMAGE_SIZE - fm.getHeight()) / 2 + fm.getAscent());
        } finally {
            g.dispose();
        }
        return placeholder;
    }

    private static BufferedImage getRuntimeDefaultImage() {
        BufferedImage result = runtimeDefaultImage;
        if (result == null) {
            synchronized (defaultImageLock) {
                result = runtimeDefaultImage;
                if (result == null) {
                    BufferedImage img = new BufferedImage(FIXED_IMAGE_SIZE, FIXED_IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = img.createGraphics();
                    try {
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect(0, 0, FIXED_IMAGE_SIZE, FIXED_IMAGE_SIZE);
                        g.setColor(Color.BLACK);
                        String text = "Sin imagen";
                        FontMetrics fm = g.getFontMetrics();
                        int textWidth = fm.stringWidth(text);
                        g.drawString(text, (FIXED_IMAGE_SIZE - textWidth) / 2, (FIXED_IMAGE_SIZE - fm.getHeight()) / 2 + fm.getAscent());
                    } finally {
                        g.dispose();
                    }
                    runtimeDefaultImage = img;
                    result = img;
                }
            }
        }
        return result;
    }

    public void loadImage(File imageFile) {
        if (Beans.isDesignTime()) {
            return;
        }

        BufferedImage img = null;
        if (imageFile != null && imageFile.exists()) {
            try {
                img = ImageIO.read(imageFile);
            } catch (IOException e) {
                img = null;
            }
        }

        if (img != null) {
            this.currentImage = img;
            this.currentImageFile = imageFile;
        } else {
            this.currentImage = getRuntimeDefaultImage();
            this.currentImageFile = null;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage imageToDraw = this.currentImage;

        if (imageToDraw == null) {
            imageToDraw = Beans.isDesignTime() ? createDesignTimePlaceholder() : getRuntimeDefaultImage();
        }

        if (imageToDraw == null) {
            drawErrorPlaceholder(g, "Cannot render image");
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int targetW = FIXED_IMAGE_SIZE;
        int targetH = FIXED_IMAGE_SIZE;
        int drawX = (panelWidth - targetW) / 2;
        int drawY = (panelHeight - targetH) / 2;

        if (g instanceof Graphics2D) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(imageToDraw, drawX, drawY, targetW, targetH, null);
        } else {
            g.drawImage(imageToDraw, drawX, drawY, targetW, targetH, this);
        }
    }

    private void drawErrorPlaceholder(Graphics g, String message) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int drawX = (panelWidth - FIXED_IMAGE_SIZE) / 2;
        int drawY = (panelHeight - FIXED_IMAGE_SIZE) / 2;

        Shape oldClip = g.getClip();
        g.clipRect(drawX, drawY, FIXED_IMAGE_SIZE, FIXED_IMAGE_SIZE);

        g.setColor(Color.RED);
        g.fillRect(drawX, drawY, FIXED_IMAGE_SIZE, FIXED_IMAGE_SIZE);
        g.setColor(Color.WHITE);
        g.drawString("Error:", drawX + 5, drawY + 15);
        g.drawString(message, drawX + 5, drawY + 30);

        g.setClip(oldClip);
    }

    public File getCurrentImageFile() {
        return currentImageFile;
    }

    public void clearImage() {
        if (Beans.isDesignTime()) {
            return;
        }
        this.currentImage = getRuntimeDefaultImage();
        this.currentImageFile = null;
        repaint();
    }

    public byte[] getImageBytes() {
        if (currentImage == null) {
            return new byte[0];
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(currentImage, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            return new byte[0]; // en caso de error, regresa arreglo vacío
        }
    }

    // Setter de la imagen desde un byte[]
    public void setImageBytes(byte[] data) {
        if (data == null || data.length == 0) {
            this.currentImage = getRuntimeDefaultImage();
            this.currentImageFile = null;
        } else {
            try (ByteArrayInputStream bais = new ByteArrayInputStream(data)) {
                BufferedImage img = ImageIO.read(bais);
                if (img != null) {
                    this.currentImage = img;
                    this.currentImageFile = null;
                } else {
                    this.currentImage = getRuntimeDefaultImage();
                }
            } catch (IOException e) {
                this.currentImage = getRuntimeDefaultImage();
            }
        }
        repaint();
    }
}
