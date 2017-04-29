from django.db import models
from slugify import slugify
from ckeditor.fields import RichTextField


class JennModel(models.Model):
    def __str__(self):
        return self.name

    class Meta:
        abstract = True


class Project(JennModel):
    name = models.CharField(max_length=200)
    description = models.CharField(max_length=500)
    pubDate = models.DateTimeField()


class PublicationType(JennModel):
    name = models.CharField(max_length=100)


class Publication(JennModel):
    name = models.CharField(max_length=200)
    abstract = models.CharField(max_length=500)
    type = models.ForeignKey(PublicationType, on_delete=models.PROTECT)
    authors = models.CharField(max_length=200)
    pubDate = models.DateField()
    isbn = models.CharField(max_length=20, blank=True)


class Section(JennModel):
    name = models.CharField(max_length=30)
    title = models.CharField(max_length=200)
    content = RichTextField()

    def getLink(self):
        return "#"+slugify(self.name)
