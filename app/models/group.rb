# Model Group
# Table groups
class Group < ActiveRecord::Base
  belongs_to :semester
  has_many :users
end
