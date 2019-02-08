package controller;

/**
 * Represents all the Game Controllers.
 *
 */
public interface Controller {

    /**
     * Returns a Runnable able to set the desidered language, to run over the JavaFX Application Thread.
     * 
     * @param lang the desidered language
     * @return a Runnable able to change the language.
     */
    Runnable createLanguageChanger(Language lang);
}
