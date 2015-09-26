package ru.mai.app.presentation.mf_boilerplate;

import android.content.res.Resources;

/** @see WithModuleFactory */
public abstract class ModuleFactory<T> {
  protected abstract Object createDaggerModule(Resources resources, T screen);
}
