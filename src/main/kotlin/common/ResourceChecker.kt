package common

class ResourceChecker {
    companion object {
        private const val BYTES_TO_MB_DIVISOR = (1024 * 1024).toDouble()
        private var previousMeasurement = 0.0

        /**
         * Forces the GC to remove unused memory if [gc] then returns allocated memory in MB
         */
        fun memoryUsage(gc: Boolean = true): Double {
            val runtime = Runtime.getRuntime()
            if (gc) {
                runtime.gc()
            }

            previousMeasurement = runtime.totalMemory().toDouble() / BYTES_TO_MB_DIVISOR

            return previousMeasurement
        }

        /**
         * Returns the change in memory use since last call of [memoryUsage] or [memoryChange]
         * If [gc] is true then the garbage collector will be run before taking measurement
         */
        fun memoryChange(gc: Boolean = true): Double {
            val runtime = Runtime.getRuntime()
            if (gc) {
                runtime.gc()
            }

            val oldPreviousMeausrement = previousMeasurement
            previousMeasurement = runtime.totalMemory().toDouble() / BYTES_TO_MB_DIVISOR

            return previousMeasurement - oldPreviousMeausrement
        }
    }
}
