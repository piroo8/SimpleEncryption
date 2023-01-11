import javax.swing.*; 
/**
 * Simple Encryption
 *
 * Program will encrypt and decrypt any phrase the user inputs using alphabetic rotation.
 * 
 * @author Pierre Ishak
 * 
 * @version Nov 23, 2020
 * 
 */
public class SimpleEncryptionIshP  
{  
  public static boolean isCancelPressed = false; // class variable to keep track of cancel button press, assume not pressed
  
  public static void main(String[] args)  
  {
    String phrase,choice,cipherText;
    int rotationAmount;
    
    printProgramDescription();
    phrase = getPhrase();
    rotationAmount = getRotationAmount();
    choice = getChoiceToEncryptOrDecrypt();
    cipherText = encryptOrDecrypt(phrase,rotationAmount,choice);
    displayCipherTextAndOrginalPhrase(phrase,cipherText);
  }
  /**
   * Prints out program description to the user
   */
  public static void printProgramDescription()
  {
    //Prints Program Description
    JOptionPane.showMessageDialog(null,"Hello!\nThis program will encrypt and decrypt any phrase the user inputs using alphabetic rotation.","Word Frequency",JOptionPane.INFORMATION_MESSAGE);
  }
  /**
   * Gets rotation amount that will be used to encrypt or decrypt the phrase
   * 
   * @return The number of alpabtical character rotations that the user wants
   */
  public static String getPhrase()
  {
    String wordStr, word = ""; 
    
    do
    {
      wordStr=JOptionPane.showInputDialog(null,"Please enter the phrase you want to encrypt or decrypt: ", "Simple Encryption",JOptionPane.QUESTION_MESSAGE);
      
      if (wordStr == null)
      {
        isCancelPressed = true;  //set sentinel to indicate cancel button was pressed
        break;
      }
      
      word = wordStr;
      
    }while(word != wordStr);  // As long as input wasn't stored
    
    word = word.toUpperCase();
    
    return word;
  }
  /**
   * Gets phrase that will be encrypted or decrypted from the user
   * 
   * @return Phrase that will be encrypted or decrypted
   */
  public static int getRotationAmount()
  {
    String numStr = "0", errMsg;
    int num = 0;
    Boolean isNotWholeNum = true;  
    Boolean catchRun = false; 
    
    if (isCancelPressed==false) //if cancel button not pressed
    {
      do
      {
        numStr=JOptionPane.showInputDialog(null,"Enter the rotation amount (1-25)", "Word Frequency",JOptionPane.QUESTION_MESSAGE);
        
        errMsg = "Sorry, \" " + numStr + " \" is not a number between 1 and 25";
        
        if (numStr == null)
        {
          isCancelPressed = true;  //set sentinel to indicate cancel button was pressed
          break;
        }
        
        try
        {
          num = Integer.parseInt(numStr); // Convert the year from a String to an int
          isNotWholeNum= false;
        }                              
        catch (NumberFormatException error)  
        {
          catchRun = true; 
        }
        
        if (num > 25 || num < 1 || catchRun == true)
        {
          JOptionPane.showMessageDialog(null,errMsg,"Word Frequency",JOptionPane.ERROR_MESSAGE);
        }
        
        catchRun = false;
        
      }while(num > 25 || num < 1 || catchRun == true);  // As long as input is not between 1 and 25 or is not an int repeat
    }
    return num;
  }
  /**
   * Gets if the user wants to encrypt or decrpyt thier phrase
   * 
   * @return The choice of encrypting or decrypting
   */
  public static String getChoiceToEncryptOrDecrypt()
  {
    String[] choices = {"Encryption","Decryption"};
    String choice = "";
    if (isCancelPressed==false) //if cancel button not pressed
    {
      choice = (String)JOptionPane.showInputDialog(null,"Select if you want to encrypt or decrypt your phrase", "Simple Encryption", JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
    }
    return choice;
  }
  /**
   * Encrypts or Decrypts the phrase
   * 
   * @param phrase The phrase that will be encrypted or decrypted
   * @param rotationAmount The number of alpabtical character rotations that the user wants
   * @param choice The choice of encrypting or decrypting
   * 
   * @return The cipher text of the phrase
   */
  public static String encryptOrDecrypt(String phrase,int rotationAmount,String choice)
  {
    String cipherText = "";
    int singleChar = 0;
    if (isCancelPressed==false) //if cancel button not pressed
    {
      for (int i = 0; i < phrase.length(); i++)
      {
        if (phrase.charAt(i) >= 65 && phrase.charAt(i) <= 90)
        {
          if (choice.equals("Encryption"))
          {
            if (((int)phrase.charAt(i)) + rotationAmount > 90)
            {
              singleChar = ((int)phrase.charAt(i)) + rotationAmount - 26;
            }
            else
            {
              singleChar = ((int)phrase.charAt(i)) + rotationAmount;
            }
          }
          if (choice.equals("Decryption"))
          { 
            if (((int)phrase.charAt(i)) + rotationAmount < 65)
            {
              singleChar = ((int)phrase.charAt(i)) - rotationAmount + 26;
            }
            else
            {
              singleChar = ((int)phrase.charAt(i)) - rotationAmount;
            }
          }
        }
        else
        {
          singleChar = phrase.charAt(i);
        }
        cipherText += (char)singleChar;
      }
    }
    return cipherText;
  }
  /**
   * Prints out the cipher text vs. phrase
   * 
   * @param frequency The number of occurences of the word in the passage
   * @param passage The passage that the word would include
   * @param word The word that will be looked for in the passage
   */
  public static void displayCipherTextAndOrginalPhrase(String phrase,String cipherText)
  {
    if (isCancelPressed==false) //if cancel button not pressed
    {
      JOptionPane.showMessageDialog(null,"Plain Text: " + phrase + "\nCipher Text: " + cipherText,"Word Frequency",JOptionPane.INFORMATION_MESSAGE);
    }
  }
}