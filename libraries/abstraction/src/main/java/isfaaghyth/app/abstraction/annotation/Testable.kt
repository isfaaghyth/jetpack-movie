package isfaaghyth.app.abstraction.annotation

@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class OpenClass

@OpenClass
@Target(AnnotationTarget.CLASS)
annotation class Testable