package util;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Paladino
 */
public class TeclasPermitidasLetras extends PlainDocument {
    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException{
        
        super.insertString(offset, str.replaceAll("[^A-Z|^a-z|^ ]",""), attr);
        
    }
    
    public void replace(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException{
        
        super.insertString(offset, str.replaceAll("[^A-Z|^a-z|^ ]",""), attr);
        
    }
}