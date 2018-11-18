package com.parassidhu.trigonomania;

import android.os.AsyncTask;

public class AsyncWrapper {

    public static abstract class Task {
        public void before() {
            // available for hire
        }

        public abstract void during();

        public void after() {
            // available for hire
        }

        public void cancelled() {
            // available for hire
        }
    }

    private AsyncWrapper() {
        // restrict instantiation
    }

    public static void run(final Task task) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                task.before();
            }

            @Override
            protected Void doInBackground(Void... params) {
                task.during();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                task.after();
            }

            @Override
            protected void onCancelled() {
                task.cancelled();
            }
        }.execute();
    }
}